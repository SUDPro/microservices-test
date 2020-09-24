package ru.itis.services;

import org.springframework.http.ResponseEntity;
import ru.itis.dtos.OrderDto;
import ru.itis.models.Order;

import java.util.Optional;

public interface OrderService {

    ResponseEntity<String> saveOrderAndSendToBroker(OrderDto order, long authorization);

    Optional<Order> getOrderById(long id);

    void save(Order order);
}
