package ua.tqs.lab6_1testcontainers;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FlywayMigrationTest {

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:12")
            .withUsername("duke")
            .withPassword("password")
            .withDatabaseName("test");

    @DynamicPropertySource
    static void configureDataSource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.PostgreSQLDialect");
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Order(1)
    void flywayShouldHaveInsertedInitialCustomers() {
        List<Customer> customers = customerRepository.findAll();

        // Verifica se os dados do V002__Insert_Sample_Customers.sql estão presentes
        assertThat(customers).hasSizeGreaterThanOrEqualTo(3);

        assertThat(customers).extracting(Customer::getName)
                .contains("Alice", "Bob", "Charlie");

        assertThat(customers).extracting(Customer::getEmail)
                .contains("alice@example.com", "bob@example.com", "charlie@example.com");

        System.out.println("✅ Flyway executou as migrações e inseriu os dados corretamente!");
    }
}
