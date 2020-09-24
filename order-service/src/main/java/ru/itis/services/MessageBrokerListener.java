package ru.itis.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.config.BrokerConfiguration;
import ru.itis.dtos.OrderProductResponseDto;
import ru.itis.enums.OrderStatus;
import ru.itis.models.Order;

@Component
public class MessageBrokerListener {

    private OrderService orderService;

    @Autowired
    public MessageBrokerListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = BrokerConfiguration.REPLY_ORDER_QUEUE_NAME)
    public void receiveMessage(OrderProductResponseDto responseDto) {
        Order order = orderService.getOrderById(responseDto.getId()).orElseThrow(IllegalArgumentException::new);
        if (responseDto.isStatus()) {
            order.setStatus(OrderStatus.INPROGRESS);
        } else {
            order.setStatus(OrderStatus.REJECTED);
        }
        orderService.save(order);
    }
}