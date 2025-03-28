package ua.tqs.lab6_1testcontainers;

import org.springframework.boot.SpringApplication;

public class TestLab61testcontainersApplication {

	public static void main(String[] args) {
		SpringApplication.from(Lab61testcontainersApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
