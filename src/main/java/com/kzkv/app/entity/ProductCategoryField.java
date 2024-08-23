package com.kzkv.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
public class ProductCategoryField {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonIgnore
    private UUID productId;

    @ManyToOne()
    @JoinColumn(name = "category_field_id")
    private CategoryField categoryField;

    @Column(name = "category_field_value")
    private String value;
}
