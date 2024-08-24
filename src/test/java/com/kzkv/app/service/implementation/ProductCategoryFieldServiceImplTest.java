package com.kzkv.app.service.implementation;

import com.kzkv.app.entity.Product;
import com.kzkv.app.entity.ProductCategoryField;
import com.kzkv.app.repository.ProductCategoryFieldRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductCategoryFieldServiceImplTest {

    @Mock
    private ProductCategoryFieldRepository productCategoryFieldRepository;

    @InjectMocks
    private ProductCategoryFieldServiceImpl productCategoryFieldService;

    private ProductCategoryField productCategoryField;
    private UUID productCategoryFieldId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productCategoryFieldId = UUID.randomUUID();
        productCategoryField = ProductCategoryField.builder()
                .id(productCategoryFieldId)
                .product(Product.builder().id(UUID.randomUUID()).build())
                .value("test")
                .build();
    }

    @Test
    void testGetProductCategoryFieldById() {
        when(productCategoryFieldRepository.findById(productCategoryFieldId)).thenReturn(Optional.of(productCategoryField));

        Optional<ProductCategoryField> foundProductCategoryField = productCategoryFieldService.getProductCategoryFieldById(productCategoryFieldId);
        assertTrue(foundProductCategoryField.isPresent());
        assertEquals("test", foundProductCategoryField.get().getValue());
    }

    @Test
    void testGetAllProductCategoryFieldsByProductId() {
        UUID productId = productCategoryField.getProduct().getId();
        when(productCategoryFieldRepository.findAllByProductId(productId)).thenReturn(List.of(productCategoryField));

        List<ProductCategoryField> productCategoryFields = productCategoryFieldService.getAllProductCategoryFieldsByProductId(productId);
        assertFalse(productCategoryFields.isEmpty());
        assertEquals(1, productCategoryFields.size());
        assertEquals("test", productCategoryFields.get(0).getValue());
    }

    @Test
    void testGetAllProductCategoryFields() {
        when(productCategoryFieldRepository.findAll()).thenReturn(List.of(productCategoryField));

        List<ProductCategoryField> productCategoryFields = productCategoryFieldService.getAllProductCategoryFields();
        assertFalse(productCategoryFields.isEmpty());
        assertEquals(1, productCategoryFields.size());
        assertEquals("test", productCategoryFields.get(0).getValue());
    }

    @Test
    void testAddProductCategoryField() {
        when(productCategoryFieldRepository.save(any(ProductCategoryField.class))).thenReturn(productCategoryField);

        ProductCategoryField savedProductCategoryField = productCategoryFieldService.addProductCategoryField(productCategoryField);
        assertNotNull(savedProductCategoryField);
        assertEquals("test", savedProductCategoryField.getValue());
    }

    @Test
    void testEditProductCategoryField() {
        when(productCategoryFieldRepository.save(any(ProductCategoryField.class))).thenReturn(productCategoryField);

        ProductCategoryField editedProductCategoryField = productCategoryFieldService.editProductCategoryField(productCategoryFieldId, productCategoryField);
        assertNotNull(editedProductCategoryField);
        assertEquals(productCategoryFieldId, editedProductCategoryField.getId());
        assertEquals("test", editedProductCategoryField.getValue());
    }

    @Test
    void testExistsById() {
        when(productCategoryFieldRepository.existsById(productCategoryFieldId)).thenReturn(true);

        boolean exists = productCategoryFieldService.existsById(productCategoryFieldId);
        assertTrue(exists);
    }
}
