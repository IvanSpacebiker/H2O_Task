package com.kzkv.app.service.implementation;

import com.kzkv.app.entity.Product;
import com.kzkv.app.repository.ProductRepository;
import com.kzkv.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategoryId(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(UUID id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public boolean existsById(UUID id) {
        return productRepository.existsById(id);
    }

}

