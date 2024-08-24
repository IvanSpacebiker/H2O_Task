package com.kzkv.app.service.implementation;

import com.kzkv.app.entity.Category;
import com.kzkv.app.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = Category.builder()
                .id(1L)
                .name(Category.Type.PC)
                .build();
    }

    @Test
    void testGetCategoryById() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Optional<Category> foundCategory = categoryService.getCategoryById(1L);
        assertTrue(foundCategory.isPresent());
        assertEquals(Category.Type.PC, foundCategory.get().getName());
    }

    @Test
    void testGetCategoryByName() {
        when(categoryRepository.findByName(Category.Type.PC)).thenReturn(Optional.of(category));

        Optional<Category> foundCategory = categoryService.getCategoryByName(Category.Type.PC);
        assertTrue(foundCategory.isPresent());
        assertEquals(1L, foundCategory.get().getId());
    }

    @Test
    void testGetAllCategories() {
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<Category> categories = categoryService.getAllCategories();
        assertFalse(categories.isEmpty());
        assertEquals(1, categories.size());
        assertEquals(Category.Type.PC, categories.get(0).getName());
    }

    @Test
    void testAddCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category savedCategory = categoryService.addCategory(category);
        assertNotNull(savedCategory);
        assertEquals(Category.Type.PC, savedCategory.getName());
    }

    @Test
    void testEditCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category editedCategory = categoryService.editCategory(1L, category);
        assertNotNull(editedCategory);
        assertEquals(1L, editedCategory.getId());
        assertEquals(Category.Type.PC, editedCategory.getName());
    }

    @Test
    void testExistsById() {
        when(categoryRepository.existsById(1L)).thenReturn(true);

        boolean exists = categoryService.existsById(1L);
        assertTrue(exists);
    }
}
