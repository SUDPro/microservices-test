package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dtos.LoginDto;
import ru.itis.dtos.RegisterDto;
import ru.itis.dtos.TokenDto;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;
import ru.itis.utils.TokenUtil;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder encoder;

    private TokenUtil tokenUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, TokenUtil tokenUtil) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.tokenUtil = tokenUtil;
    }

    @Override
    public TokenDto login(LoginDto loginDto) {
        Optional<User> candidate = userRepository.findUserByLogin(loginDto.getLogin());
        if (candidate.isPresent()) {
            User customer = candidate.get();
            if (encoder.matches(loginDto.getPassword(), customer.getPasswordHash())) {
                String token = tokenUtil.generateToken(customer);
                return TokenDto.from(token, customer.getId(), customer.getLogin());
            }
        }
        throw new SecurityException("Login attempt failed");
    }

    @Override
    public TokenDto register(RegisterDto customerDto) {
        User user = addUser(customerDto);
        String token = tokenUtil.generateToken(user);
        return TokenDto.from(token, user.getId(), user.getLogin());
    }

    private User addUser(RegisterDto customerDto) {
        if (!customerDto.getLogin().equals("") && !userRepository.existsByLogin(customerDto.getLogin())) {
            return userRepository.save(User.builder()
                    .login(customerDto.getLogin())
                    .passwordHash(encoder.encode(customerDto.getPassword()))
                    .name(customerDto.getName())
                    .build());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
