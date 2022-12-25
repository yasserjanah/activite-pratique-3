package me.janah.orderservice.entities;

import me.janah.orderservice.enums.OrderStatus;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "fullOrder", types = Order.class)
public interface OrderProjection {
    Long getId();
    Date getCreatedAt();
    OrderStatus getStatus();
    Long getCustomerId();
}
