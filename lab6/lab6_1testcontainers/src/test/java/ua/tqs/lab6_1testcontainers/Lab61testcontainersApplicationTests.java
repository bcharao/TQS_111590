package ua.tqs.lab6_1testcontainers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class Lab61testcontainersApplicationTests {

	@Test
	void contextLoads() {
	}

}
