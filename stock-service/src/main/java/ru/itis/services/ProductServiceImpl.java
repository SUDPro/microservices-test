package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itis.dtos.OrderProductDto;
import ru.itis.dtos.OrderProductResponseDto;
import ru.itis.dtos.ProductForm;
import ru.itis.models.Product;
import ru.itis.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<String> add(ProductForm productForm) {
        if (!repository.findByName(productForm.getName()).isPresent()) {
           repository.save(Product.builder()
                    .name(productForm.getName())
                    .description(productForm.getDescription())
                    .build());
            return new ResponseEntity<>("Product saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Product with this name already exist", HttpStatus.CONFLICT);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = repository.findAll();
        return repository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public OrderProductResponseDto validate(OrderProductDto requestDto) {
        if (repository.existsById(requestDto.getProductId())){
            return new OrderProductResponseDto(requestDto.getId(), true);
        } else {
            return new OrderProductResponseDto(requestDto.getId(), false);
        }
    }
}