package com.delacasa.courses.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Immutable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(fetch = LAZY, mappedBy = "teacher")
    private Set<Course> courses;

    @OneToMany(fetch = LAZY, mappedBy = "teacher")
    private Set<TeacherHasDegree> degrees;

}
