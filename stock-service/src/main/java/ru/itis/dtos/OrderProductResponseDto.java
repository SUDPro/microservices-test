package ru.itis.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NegativeOrZero;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResponseDto {

    private long id;
    private boolean status;
}
