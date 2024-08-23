package com.kzkv.app.service.implementation;

import com.kzkv.app.entity.ProductCategoryField;
import com.kzkv.app.repository.ProductCategoryFieldRepository;
import com.kzkv.app.service.ProductCategoryFieldService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductCategoryFieldServiceImpl implements ProductCategoryFieldService {
    
    private ProductCategoryFieldRepository productCategoryFieldRepository;

    @Override
    public Optional<ProductCategoryField> getProductCategoryFieldById(UUID id) {
        return productCategoryFieldRepository.findById(id);
    }

    @Override
    public List<ProductCategoryField> getAllProductCategoryFieldsByProductId(UUID id) {
        return productCategoryFieldRepository.findAllByProductId(id);
    }

    @Override
    public List<ProductCategoryField> getAllProductCategoryFields() {
        return productCategoryFieldRepository.findAll();
    }

    @Override
    public ProductCategoryField addProductCategoryField(ProductCategoryField productCategoryField) {
        return productCategoryFieldRepository.save(productCategoryField);
    }

    @Override
    public ProductCategoryField editProductCategoryField(UUID id, ProductCategoryField productCategoryField) {
        productCategoryField.setId(id);
        return productCategoryFieldRepository.save(productCategoryField);
    }

    @Override
    public boolean existsById(UUID id) {
        return productCategoryFieldRepository.existsById(id);
    }

}
