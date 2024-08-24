package com.kzkv.app.repository;

import com.kzkv.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategoryId(Long categoryId);
}
