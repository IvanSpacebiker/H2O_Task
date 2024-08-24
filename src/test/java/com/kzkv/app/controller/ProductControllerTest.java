package com.kzkv.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kzkv.app.entity.Category;
import com.kzkv.app.entity.CategoryField;
import com.kzkv.app.entity.Product;
import com.kzkv.app.entity.ProductCategoryField;
import com.kzkv.app.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    private static Product product;
    private static UUID productId;

    @BeforeAll
    static void setUp() {
        productId = UUID.randomUUID();
        Category category = Category.builder()
                .id(1L)
                .name(Category.Type.PC)
                .build();

        List<CategoryField> categoryFields = List.of(
                CategoryField.builder().id(1L).categoryId(1L).name("form-factor").build()
        );

        product = Product.builder()
                .id(productId)
                .serialNumber("SN123456")
                .manufacturer("Test Manufacturer")
                .price(1000.0)
                .stockQuantity(10)
                .category(category)
                .build();

        List<ProductCategoryField> productCategoryFields = categoryFields.stream().map(field ->
                new ProductCategoryField(UUID.randomUUID(), product, field, "desktop")
        ).toList();

        product.setProductCategoryFields(productCategoryFields);
    }


    @Test
    void testGetProductById_Success() throws Exception {
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productId.toString()))
                .andExpect(jsonPath("$.serialNumber").value("SN123456"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"))
                .andExpect(jsonPath("$.price").value(1000.0))
                .andExpect(jsonPath("$.stockQuantity").value(10))
                .andExpect(jsonPath("$.category.name").value("PC"))
                .andExpect(jsonPath("$.productCategoryFields[0].value").value("desktop"));
    }

    @Test
    void testGetProductById_NotFound() throws Exception {
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/products/{id}", productId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllProducts_Success() throws Exception {
        List<Product> products = Collections.singletonList(product);
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(productId.toString()))
                .andExpect(jsonPath("$[0].serialNumber").value("SN123456"))
                .andExpect(jsonPath("$[0].manufacturer").value("Test Manufacturer"))
                .andExpect(jsonPath("$[0].price").value(1000.0))
                .andExpect(jsonPath("$[0].stockQuantity").value(10))
                .andExpect(jsonPath("$[0].category.name").value("PC"))
                .andExpect(jsonPath("$[0].productCategoryFields[0].value").value("desktop"));
    }

    @Test
    void testAddProduct_Success() throws Exception {
        when(productService.addProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productId.toString()))
                .andExpect(jsonPath("$.serialNumber").value("SN123456"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"))
                .andExpect(jsonPath("$.price").value(1000.0))
                .andExpect(jsonPath("$.stockQuantity").value(10))
                .andExpect(jsonPath("$.category.name").value("PC"))
                .andExpect(jsonPath("$.productCategoryFields[0].value").value("desktop"));
    }

    @Test
    void testUpdateProduct_Success() throws Exception {
        when(productService.existsById(productId)).thenReturn(true);
        when(productService.editProduct(eq(productId), any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productId.toString()))
                .andExpect(jsonPath("$.serialNumber").value("SN123456"))
                .andExpect(jsonPath("$.manufacturer").value("Test Manufacturer"))
                .andExpect(jsonPath("$.price").value(1000.0))
                .andExpect(jsonPath("$.stockQuantity").value(10))
                .andExpect(jsonPath("$.category.name").value("PC"))
                .andExpect(jsonPath("$.productCategoryFields[0].value").value("desktop"));
    }

    @Test
    void testUpdateProduct_NotFound() throws Exception {
        when(productService.existsById(productId)).thenReturn(false);

        mockMvc.perform(put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isNotFound());
    }
}
