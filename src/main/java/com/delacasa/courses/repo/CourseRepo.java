package com.delacasa.courses.repo;

import com.delacasa.courses.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;
import java.util.Optional;

@GraphQlRepository
public interface CourseRepo extends JpaRepository<Course, Integer>,
        QuerydslPredicateExecutor<Course> {

    Optional<Course> findByName(String name);

    List<Course> findByTeacherLastName(String teacherLastName);

}
