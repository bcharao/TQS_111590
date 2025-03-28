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
class ApplicationTests {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:12")
            .withUsername("duke")
            .withPassword("password")
            .withDatabaseName("test");

    @Autowired
    private CustomerRepository customerRepository;

    // requires Spring Boot >= 2.2.6
    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
       // registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.PostgreSQLDialect");

    }

    @Test
    @Order(1) // ✅ Roda primeiro para garantir que os registros existem
    void testDatabaseHasInitialCustomers() {
        // Buscar todos os clientes na base de dados
        List<Customer> customers = customerRepository.findAll();

        // Verificar se os clientes iniciais estão na base de dados
        assertThat(customers).hasSizeGreaterThanOrEqualTo(3);
        assertThat(customers).extracting(Customer::getEmail)
                .contains("alice@example.com", "bob@example.com", "charlie@example.com");

        System.out.println("✅ Banco de dados contém os registros iniciais!");
    }

    @Test
    @Order(2)
    void contextLoads() {

        Customer customer = new Customer();
        customer.setName("Testcontainers");
        customer.setEmail("joao@example.com");

        customerRepository.save(customer);

        System.out.println("Context loads!");
    }
    @Test
    @Order(3)
    void testSaveCustomer() {
        // Criar e salvar um novo cliente
        Customer customer = new Customer();
        customer.setName("Testcontainers");
        customer.setEmail("testcontainers@example.com");

        customerRepository.save(customer);

        // Verificar se o cliente foi salvo
        assertThat(customerRepository.findByEmail("testcontainers@example.com")).isPresent();

        System.out.println("✅ Cliente salvo com sucesso!");
    }

    @Test
    @Order(4)
    void testFindCustomerByName() {
        // Buscar cliente salvo no teste anterior
        Customer foundCustomer = customerRepository.findByEmail("testcontainers@example.com").orElse(null);

        // Verificar se os dados estão corretos
        assertThat(foundCustomer).isNotNull();
        assertThat(foundCustomer.getName()).isEqualTo("Testcontainers");

        System.out.println("✅ Cliente encontrado pelo nome com sucesso!");
    }

    @Test
    @Order(5)
    void testDeleteCustomer() {
        // Buscar cliente salvo
        Customer foundCustomer = customerRepository.findByEmail("testcontainers@example.com").orElse(null);

        // Remover cliente
        assertThat(foundCustomer).isNotNull();
        customerRepository.delete(foundCustomer);

        // Verificar se o cliente foi removido
        assertThat(customerRepository.findByEmail("testcontainers@example.com")).isEmpty();

        System.out.println("✅ Cliente removido com sucesso!");
    }

}
