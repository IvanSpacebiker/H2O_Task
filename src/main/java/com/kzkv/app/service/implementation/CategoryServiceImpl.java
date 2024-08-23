package com.kzkv.app.service.implementation;

import com.kzkv.app.entity.Category;
import com.kzkv.app.repository.CategoryRepository;
import com.kzkv.app.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(Long id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }
}
