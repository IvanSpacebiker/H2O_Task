package com.kzkv.app.service;

import com.kzkv.app.entity.ProductCategoryField;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCategoryFieldService {

    Optional<ProductCategoryField> getProductCategoryFieldById(UUID id);
    List<ProductCategoryField> getAllProductCategoryFieldsByProductId(UUID id);
    List<ProductCategoryField> getAllProductCategoryFields();
    ProductCategoryField addProductCategoryField(ProductCategoryField productCategoryField);
    ProductCategoryField editProductCategoryField(UUID id, ProductCategoryField productCategoryField);
    boolean existsById(UUID id);


}
