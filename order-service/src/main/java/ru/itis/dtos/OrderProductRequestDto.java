package ru.itis.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderProductRequestDto {

    private long id;
    private long productId;
}