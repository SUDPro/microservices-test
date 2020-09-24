package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
    public ResponseEntity add(ProductForm productForm) {
        if (!repository.findByName(productForm.getName()).isPresent()) {
            repository.save(Product.builder()
                    .name(productForm.getName())
                    .description(productForm.getDescription())
                    .build());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}