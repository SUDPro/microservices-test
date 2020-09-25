package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itis.dtos.OrderDto;
import ru.itis.dtos.OrderProductRequestDto;
import ru.itis.enums.OrderStatus;
import ru.itis.models.Order;
import ru.itis.repositories.OrderRepository;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private MessageBrokerSender messageBrokerSender;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MessageBrokerSender messageBrokerSender) {
        this.orderRepository = orderRepository;
        this.messageBrokerSender = messageBrokerSender;
    }

    @Override
    public ResponseEntity<String> saveOrderAndSendToBroker(OrderDto order, long userId) {
        Order orderFromDB = orderRepository.save(Order.builder()
                .productId(order.getProductId())
                .userId(userId)
                .status(OrderStatus.WAITING)
                .build());
        messageBrokerSender.sendMessage(new OrderProductRequestDto(orderFromDB.getId(), orderFromDB.getProductId()));
        return ResponseEntity.ok().build();
    }

    @Override
    public Optional<Order> getOrderById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}