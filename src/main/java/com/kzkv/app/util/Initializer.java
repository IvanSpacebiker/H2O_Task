package com.kzkv.app.util;

import com.kzkv.app.entity.Category;
import com.kzkv.app.entity.CategoryField;
import com.kzkv.app.repository.CategoryFieldRepository;
import com.kzkv.app.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class Initializer {

    public CategoryRepository categoryRepository;
    public CategoryFieldRepository categoryFieldRepository;

    @Bean
    public void init() {
        String[] categoriesProperties = {"form-factor", "inch-size", "diagonal", "capacity"};
        for (var type : Category.Type.values()) {
            categoryRepository.save(new Category(type));
            categoryFieldRepository.save(
                    CategoryField.builder()
                            .name(categoriesProperties[type.ordinal()])
                            .categoryId(type.ordinal() + 1L)
                            .build()
            );
        }

    }


}
