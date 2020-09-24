package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itis.dtos.LoginDto;
import ru.itis.dtos.RegisterDto;
import ru.itis.dtos.TokenDto;
import ru.itis.services.UserService;

@Controller
public class LoginRegisterController {

    private UserService userService;

    @Autowired
    public LoginRegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        try {
            return ResponseEntity.ok(userService.login(loginDto));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<TokenDto> register(@RequestBody RegisterDto customerDto) {
        try {
            return ResponseEntity.ok(userService.register(customerDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
