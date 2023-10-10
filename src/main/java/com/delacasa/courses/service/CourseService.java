package com.delacasa.courses.service;

import com.delacasa.courses.entity.Course;
import com.delacasa.courses.model.MyPage;
import com.delacasa.courses.repo.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public MyPage<Course> getByTeacherLastName(final String teacherLastName, int page, int limit) {
        return new MyPage<>(repo.findByTeacherLastName(teacherLastName, PageRequest.of(page, limit)));

    }

}
