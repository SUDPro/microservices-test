package ru.itis.services;

import ru.itis.dtos.OrderProductRequestDto;

public interface MessageBrokerSender {

    void sendMessage(OrderProductRequestDto order);
}