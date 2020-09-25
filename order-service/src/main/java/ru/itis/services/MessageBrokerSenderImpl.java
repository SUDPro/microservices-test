package ru.itis.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.config.BrokerConfiguration;
import ru.itis.dtos.OrderProductRequestDto;

@Component
public class MessageBrokerSenderImpl implements MessageBrokerSender {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageBrokerSenderImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(OrderProductRequestDto order) {
        rabbitTemplate.convertAndSend(BrokerConfiguration.DIRECT_EXCHANGE_NAME,
                BrokerConfiguration.ORDER_ROUTING_KEY, order);
    }
}