package me.janah.customerservice;

import me.janah.customerservice.entities.Customer;
import me.janah.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.saveAll(List.of(
					Customer.builder().name("Yasser JANAH").email("yasser@janah.me").build(),
					Customer.builder().name("Yassine").email("yassine@janah.me").build(),
					Customer.builder().name("Chadi").email("chadi@janah.me").build()
			));
			customerRepository.findAll().forEach(System.out::println);
		};
	}


}
