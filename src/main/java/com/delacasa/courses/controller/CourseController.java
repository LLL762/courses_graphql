package com.delacasa.courses.controller;

import com.delacasa.courses.entity.Course;
import com.delacasa.courses.model.MyPage;
import com.delacasa.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

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
    @PreAuthorize("@userRoleServ.hasAccessLevel(authentication,'intern')")
    public MyPage<Course> courseByTeacherLastName(@Argument String lastName, @Argument int page, @Argument int limit) {
        return courseServ.getByTeacherLastName(lastName, page, limit);
    }

}
