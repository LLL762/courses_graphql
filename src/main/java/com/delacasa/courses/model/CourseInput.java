package com.delacasa.courses.model;

import com.delacasa.courses.entity.Course;
import com.delacasa.courses.entity.Degree;
import com.delacasa.courses.entity.Teacher;

import java.util.Map;

public record CourseInput(String name, String description, int degree, int teacher) {

    public Map<String, Object> toMap() {
        return Map.of("name", name, "description", description, "degree", degree, "teacher", teacher);
    }

    public Course toCourse() {
        return Course.builder()
                .name(name)
                .description(description)
                .degree(Degree.builder().id(degree).build())
                .teacher(Teacher.builder().id(teacher).build())
                .build();
    }

}
