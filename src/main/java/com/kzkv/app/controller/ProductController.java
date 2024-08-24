package com.kzkv.app.controller;

import com.kzkv.app.entity.Product;
import com.kzkv.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping ("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        return productService.getProductById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/category{categoryId}")
    ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getAllProductsByCategoryId(categoryId));
    }

    @PostMapping()
    ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody Product product) {
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.editProduct(id, product));
    }

}
