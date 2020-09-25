package ru.itis.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.config.BrokerConfiguration;
import ru.itis.dtos.OrderProductDto;

@Component
public class MessageBrokerListener {

    private MessageBrokerSender sender;

    private ProductService service;

    @Autowired
    public MessageBrokerListener(ProductService service, MessageBrokerSender sender) {
        this.sender = sender;
        this.service = service;
    }

    @RabbitListener(queues = BrokerConfiguration.ORDER_QUEUE_NAME)
    public void receiveMessage(OrderProductDto requestDto) {
        sender.sendMessage(service.validate(requestDto));
    }
}