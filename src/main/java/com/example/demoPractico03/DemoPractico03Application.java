package com.example.demoPractico03;

import com.example.demoPractico03.repository.Product;
import com.example.demoPractico03.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoPractico03Application {

	public static void main(String[] args) {
		SpringApplication.run(DemoPractico03Application.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ProductRepository repository) {
		return args -> {
			repository.save(new Product("Laptop", 1500.00));
			repository.save(new Product("Mouse", 50.00));
			repository.save(new Product("Teclado", 120.50));
			repository.save(new Product("Tapiz", 10.00));
		};
	}

}
