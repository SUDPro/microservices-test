package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dtos.OrderDto;
import ru.itis.services.OrderService;
import ru.itis.utils.TokenUtil;

@RestController
public class OrderController {

    private OrderService orderService;
    private TokenUtil tokenUtil;

    @Autowired
    public OrderController(OrderService orderService, TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public ResponseEntity<String> saveOrder(@RequestBody OrderDto order, @RequestHeader("Authorization") String authorization) {
        long userId = tokenUtil.getUserIdFromJWT(authorization);
        return orderService.saveOrderAndSendToBroker(order, userId);
    }
}