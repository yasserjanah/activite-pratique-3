package me.janah.inventoryservice;

import me.janah.inventoryservice.entities.Product;
import me.janah.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository){
		return args -> {
			productRepository.saveAll(
					List.of(
							Product.builder().name("Computer 1").price(10000).quantity(10).build(),
							Product.builder().name("Computer 2").price(20000).quantity(20).build(),
							Product.builder().name("Computer 3").price(30000).quantity(30).build(),
							Product.builder().name("Computer 4").price(40000).quantity(40).build(),
							Product.builder().name("Computer 5").price(50000).quantity(50).build(),
							Product.builder().name("Computer 6").price(60000).quantity(60).build(),
							Product.builder().name("Computer 7").price(70000).quantity(70).build(),
							Product.builder().name("Computer 8").price(80000).quantity(80).build(),
							Product.builder().name("Computer 9").price(90000).quantity(90).build(),
							Product.builder().name("Computer 10").price(100000).quantity(100).build()
					)
			);
		};
	}

}
