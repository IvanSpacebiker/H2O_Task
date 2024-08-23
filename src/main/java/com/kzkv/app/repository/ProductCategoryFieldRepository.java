package com.kzkv.app.repository;

import com.kzkv.app.entity.ProductCategoryField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductCategoryFieldRepository extends JpaRepository<ProductCategoryField, UUID> {
    List<ProductCategoryField> findAllByProductId(UUID productId);
}
