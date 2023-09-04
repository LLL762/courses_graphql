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
