package com.kzkv.app.service.implementation;

import com.kzkv.app.entity.Category;
import com.kzkv.app.entity.Product;
import com.kzkv.app.repository.ProductRepository;
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

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private UUID productId;
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productId = UUID.randomUUID();
        category = Category.builder().id(1L).name(Category.Type.PC).build();
        product = Product.builder()
                .id(productId)
                .category(category)
                .serialNumber("SN123")
                .manufacturer("Test Manufacturer")
                .price(100.0)
                .stockQuantity(50)
                .build();
    }

    @Test
    void testGetProductById() {
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(productId);
        assertTrue(foundProduct.isPresent());
        assertEquals(category, foundProduct.get().getCategory());
        assertEquals("SN123", foundProduct.get().getSerialNumber());
        assertEquals("Test Manufacturer", foundProduct.get().getManufacturer());
        assertEquals(100.0, foundProduct.get().getPrice());
        assertEquals(50, foundProduct.get().getStockQuantity());
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> products = productService.getAllProducts();
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertEquals(category, products.get(0).getCategory());
        assertEquals("SN123", products.get(0).getSerialNumber());
        assertEquals("Test Manufacturer", products.get(0).getManufacturer());
        assertEquals(100.0, products.get(0).getPrice());
        assertEquals(50, products.get(0).getStockQuantity());
    }

    @Test
    void testGetAllProductsByCategoryId() {
        when(productRepository.findAllByCategoryId(1L)).thenReturn(List.of(product));

        List<Product> products = productService.getAllProductsByCategoryId(1L);
        assertFalse(products.isEmpty());
        assertEquals(1, products.size());
        assertEquals(category, products.get(0).getCategory());
        assertEquals("SN123", products.get(0).getSerialNumber());
        assertEquals("Test Manufacturer", products.get(0).getManufacturer());
        assertEquals(100.0, products.get(0).getPrice());
        assertEquals(50, products.get(0).getStockQuantity());
    }

    @Test
    void testAddProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.addProduct(product);
        assertNotNull(savedProduct);
        assertEquals(category, savedProduct.getCategory());
        assertEquals("SN123", savedProduct.getSerialNumber());
        assertEquals("Test Manufacturer", savedProduct.getManufacturer());
        assertEquals(100.0, savedProduct.getPrice());
        assertEquals(50, savedProduct.getStockQuantity());
    }

    @Test
    void testEditProduct() {
        Category editCategory = Category.builder().id(2L).name(Category.Type.LAPTOP).build();
        Product editProduct = Product.builder()
                .id(UUID.randomUUID())
                .category(editCategory)
                .serialNumber("SN456")
                .manufacturer("Test Manufacturer 2")
                .price(200.0)
                .stockQuantity(100)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(editProduct);

        Product editedProduct = productService.editProduct(productId, editProduct);
        assertNotNull(editedProduct);
        assertEquals(productId, editedProduct.getId());
        assertEquals(editCategory, editedProduct.getCategory());
        assertEquals("SN456", editedProduct.getSerialNumber());
        assertEquals("Test Manufacturer 2", editedProduct.getManufacturer());
        assertEquals(200.0, editedProduct.getPrice());
        assertEquals(100, editedProduct.getStockQuantity());
    }

    @Test
    void testExistsById() {
        when(productRepository.existsById(productId)).thenReturn(true);

        assertTrue(productService.existsById(productId));
    }
}
