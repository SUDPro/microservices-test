package ru.itis.services;

import org.springframework.http.ResponseEntity;
import ru.itis.dtos.OrderProductDto;
import ru.itis.dtos.OrderProductResponseDto;
import ru.itis.dtos.ProductForm;
import ru.itis.models.Product;

import java.util.List;

public interface ProductService {
    ResponseEntity<String> add(ProductForm product);

    List<Product> getAllProducts();

    Product getProductById(long id);

    OrderProductResponseDto validate(OrderProductDto requestDto);
}