package ru.itis.services;

import ru.itis.dtos.LoginDto;
import ru.itis.dtos.RegisterDto;
import ru.itis.dtos.TokenDto;

public interface UserService {

    TokenDto login(LoginDto loginDto);

    TokenDto register(RegisterDto customerDto);
}