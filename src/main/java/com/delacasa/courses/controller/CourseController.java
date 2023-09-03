package com.delacasa.courses.controller;

import com.delacasa.courses.entity.Course;
import com.delacasa.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseServ;

    @QueryMapping
    public Optional<Course> courseById(@Argument int id) {
        return courseServ.getById(id);
    }

    @QueryMapping
    public Optional<Course> courseByName(@Argument String name) {
        return courseServ.getByName(name);
    }

    @QueryMapping
    public List<Course> courseByTeacherLastName(@Argument String lastName) {
        return courseServ.getByTeacherLastName(lastName);
    }
    
}
