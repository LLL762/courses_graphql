package com.delacasa.courses.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.Immutable;

import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Immutable
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
    @Column
    private String description;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "course_has_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;


}
