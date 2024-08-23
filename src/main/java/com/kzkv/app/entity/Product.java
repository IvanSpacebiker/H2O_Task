package com.kzkv.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String serialNumber;

    private String manufacturer;

    private Double price;

    private int stockQuantity;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategoryField> productCategoryFields;

}
