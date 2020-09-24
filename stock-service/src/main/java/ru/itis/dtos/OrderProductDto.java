package ru.itis.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderProductDto {

    private long id;
    private long productId;
}
