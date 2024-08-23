package com.kzkv.app.service;

import com.kzkv.app.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    Optional<Product> getProductById(UUID id);
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product editProduct(UUID id, Product product);
    boolean existsById(UUID id);
}
