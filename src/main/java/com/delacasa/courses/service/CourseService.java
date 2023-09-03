package com.delacasa.courses.service;

import com.delacasa.courses.entity.Course;
import com.delacasa.courses.repo.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepo repo;

    public Optional<Course> getById(final Integer id) {
        return repo.findById(id);
    }

    public Optional<Course> getByName(final String name) {
        return repo.findByName(name);
    }

    public List<Course> getByTeacherLastName(final String teacherLastName) {
        return repo.findByTeacherLastName(teacherLastName);
    }

}
