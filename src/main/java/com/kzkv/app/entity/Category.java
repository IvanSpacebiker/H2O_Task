package com.kzkv.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private final Type name;


    public enum Type {
        PC,
        LAPTOP,
        MONITOR,
        DRIVE;
    }
}
