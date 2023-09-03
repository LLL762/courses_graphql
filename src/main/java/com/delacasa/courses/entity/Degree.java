package com.delacasa.courses.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Getter
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

}
