package com.kzkv.app.service;

import com.kzkv.app.entity.CategoryField;

import java.util.List;
import java.util.Optional;

public interface CategoryFieldService {

    Optional<CategoryField> getCategoryFieldById(Long id);
    List<CategoryField> getAllCategoryFieldsByCategoryId(Long id);
    List<CategoryField> getAllCategoryFields();
    CategoryField addCategoryField(CategoryField categoryField);
    CategoryField editCategoryField(Long id, CategoryField categoryField);
    boolean existsById(Long id);
}
