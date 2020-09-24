package ru.itis.dtos;

import lombok.Getter;

@Getter
public class RegisterDto {
    private String name;
    private String login;
    private String password;
}