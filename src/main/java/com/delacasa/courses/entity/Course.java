package com.delacasa.courses.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
