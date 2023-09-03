package com.delacasa.courses.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Immutable
@Getter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "course_has_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;

    @OneToMany(fetch = LAZY, mappedBy = "student")
    private Set<StudentHasDegree> degrees;

}
