package com.kzkv.app.service.implementation;

import com.kzkv.app.entity.CategoryField;
import com.kzkv.app.repository.CategoryFieldRepository;
import com.kzkv.app.service.CategoryFieldService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryFieldServiceImpl implements CategoryFieldService {

    private CategoryFieldRepository categoryFieldRepository;

    @Override
    public Optional<CategoryField> getCategoryFieldById(Long id) {
        return categoryFieldRepository.findById(id);
    }

    @Override
    public List<CategoryField> getAllCategoryFieldsByCategoryId(Long id) {
        return categoryFieldRepository.findAllByCategoryId(id);
    }

    @Override
    public List<CategoryField> getAllCategoryFields() {
        return categoryFieldRepository.findAll();
    }

    @Override
    public CategoryField addCategoryField(CategoryField categoryField) {
        return categoryFieldRepository.save(categoryField);
    }

    @Override
    public CategoryField editCategoryField(Long id, CategoryField categoryField) {
        categoryField.setId(id);
        return categoryFieldRepository.save(categoryField);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryFieldRepository.existsById(id);
    }
}
