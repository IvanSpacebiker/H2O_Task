package com.kzkv.app.repository;

import com.kzkv.app.entity.CategoryField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryFieldRepository extends JpaRepository<CategoryField, Long> {
    List<CategoryField> findAllByCategoryId(Long categoryId);
}
