package com.kzkv.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class ProductCategoryField {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    @ToString.Exclude
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "category_field_id")
    private CategoryField categoryField;

    @Column(name = "category_field_value")
    private String value;
}
