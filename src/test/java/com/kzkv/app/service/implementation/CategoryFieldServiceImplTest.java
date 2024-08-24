package com.kzkv.app.service.implementation;

import com.kzkv.app.entity.CategoryField;
import com.kzkv.app.repository.CategoryFieldRepository;
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

class CategoryFieldServiceImplTest {

    @Mock
    private CategoryFieldRepository categoryFieldRepository;

    @InjectMocks
    private CategoryFieldServiceImpl categoryFieldService;

    private CategoryField categoryField;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryField = CategoryField.builder()
                .id(1L)
                .categoryId(1L)
                .name("form-factor")
                .build();
    }

    @Test
    void testGetCategoryFieldById() {
        when(categoryFieldRepository.findById(1L)).thenReturn(Optional.of(categoryField));

        Optional<CategoryField> foundCategoryField = categoryFieldService.getCategoryFieldById(1L);
        assertTrue(foundCategoryField.isPresent());
        assertEquals("form-factor", foundCategoryField.get().getName());
    }

    @Test
    void testGetAllCategoryFieldsByCategoryId() {
        when(categoryFieldRepository.findAllByCategoryId(1L)).thenReturn(List.of(categoryField));

        List<CategoryField> categoryFields = categoryFieldService.getAllCategoryFieldsByCategoryId(1L);
        assertFalse(categoryFields.isEmpty());
        assertEquals(1, categoryFields.size());
        assertEquals("form-factor", categoryFields.get(0).getName());
    }

    @Test
    void testGetAllCategoryFields() {
        when(categoryFieldRepository.findAll()).thenReturn(List.of(categoryField));

        List<CategoryField> categoryFields = categoryFieldService.getAllCategoryFields();
        assertFalse(categoryFields.isEmpty());
        assertEquals(1, categoryFields.size());
        assertEquals("form-factor", categoryFields.get(0).getName());
    }

    @Test
    void testAddCategoryField() {
        when(categoryFieldRepository.save(any(CategoryField.class))).thenReturn(categoryField);

        CategoryField savedCategoryField = categoryFieldService.addCategoryField(categoryField);
        assertNotNull(savedCategoryField);
        assertEquals("form-factor", savedCategoryField.getName());
    }

    @Test
    void testEditCategoryField() {
        when(categoryFieldRepository.save(any(CategoryField.class))).thenReturn(categoryField);

        CategoryField editedCategoryField = categoryFieldService.editCategoryField(1L, categoryField);
        assertNotNull(editedCategoryField);
        assertEquals(1L, editedCategoryField.getId());
        assertEquals("form-factor", editedCategoryField.getName());
    }

    @Test
    void testExistsById() {
        when(categoryFieldRepository.existsById(1L)).thenReturn(true);

        boolean exists = categoryFieldService.existsById(1L);
        assertTrue(exists);
    }
}
