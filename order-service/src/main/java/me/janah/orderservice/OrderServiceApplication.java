package me.janah.orderservice;

import me.janah.orderservice.entities.Order;
import me.janah.orderservice.entities.ProductItem;
import me.janah.orderservice.enums.OrderStatus;
import me.janah.orderservice.models.Customer;
import me.janah.orderservice.models.Product;
import me.janah.orderservice.repositories.OrderRepository;
import me.janah.orderservice.repositories.ProductItemRepository;
import me.janah.orderservice.services.CustomerRestClientService;
import me.janah.orderservice.services.ProductRestClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(OrderRepository orderRepository,
							ProductItemRepository productItemRepository,
							CustomerRestClientService customerRestClientService,
							ProductRestClientService productRestClientService
							){
		return args -> {
			List<Customer> customers = customerRestClientService.allCustomers().getContent().stream().toList();
			List<Product> products = productRestClientService.allProducts().getContent().stream().toList();
			customers.forEach(System.out::println);
			products.forEach(System.out::println);
			Long customerId = 1L;
			Customer customer = customerRestClientService.customerById(customerId);
			Random random = new Random();
			for (int i = 0; i < 20 ; i++){
				Order order = Order.builder()
						.customerId(customers.get(random.nextInt(customers.size())).getId())
						.status(Math.random() > 0.5 ? OrderStatus.PENDING : OrderStatus.CREATED)
						.createdAt(new Date())
						.build();
				Order savedOrder = orderRepository.save(order);
				for (Product product : products) {
					if (Math.random() > 0.70) {
						ProductItem productItem = ProductItem.builder()
								.order(savedOrder)
								.productId(product.getId())
								.price(product.getPrice())
								.quantity(1 + random.nextInt(10))
								.discount(Math.random())
								.build();
						productItemRepository.save(productItem);
					}
				}
			}
		};
	}

}
