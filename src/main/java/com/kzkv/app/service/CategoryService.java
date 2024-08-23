package com.kzkv.app.service;

import com.kzkv.app.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> getCategoryById(Long id);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category editCategory(Long id, Category category);
    boolean existsById(Long id);
}
