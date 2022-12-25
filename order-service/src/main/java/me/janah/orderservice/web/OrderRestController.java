package me.janah.orderservice.web;

import lombok.AllArgsConstructor;
import me.janah.orderservice.entities.Order;
import me.janah.orderservice.models.Customer;
import me.janah.orderservice.models.Product;
import me.janah.orderservice.repositories.OrderRepository;
import me.janah.orderservice.repositories.ProductItemRepository;
import me.janah.orderservice.services.CustomerRestClientService;
import me.janah.orderservice.services.ProductRestClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderRestController {

    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private ProductRestClientService productRestClientService;

    @GetMapping(path = "/fullOrder/{id}")
    private Order getOrder(@PathVariable Long id){
        Order order = orderRepository.findById(id).orElse(null);
        assert order != null;
        Customer customer = customerRestClientService.customerById(order.getCustomerId());
        order.setCustomer(customer);
        order.getProductItems().forEach( productItem -> {
            Product product = productRestClientService.productById(productItem.getProductId());
            productItem.setProduct(product);
        });
        return order;
    }

}
