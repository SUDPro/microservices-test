package ru.itis.services;

import ru.itis.dtos.OrderProductResponseDto;

public interface MessageBrokerSender {

    void sendMessage(OrderProductResponseDto order);
}