package com.delacasa.courses.controller;

import com.delacasa.courses.entity.*;
import com.delacasa.courses.service.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.mockito.Mockito.when;

@GraphQlTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CourseService courseServMock;

    @PostConstruct
    void postConstruct() {
        objectMapper.setSerializationInclusion(NON_NULL);
    }


    @Test
    void courseById() throws JsonProcessingException {
        final Course fake = Course.builder()
                .id(1)
                .name("name")
                .build();

        when(courseServMock.getById(1)).thenReturn(Optional.of(fake));

        graphQlTester
                .documentName("courseById")
                .variable("id", 1)
                .execute()
                .path("courseById")
                .matchesJson(objectMapper.writeValueAsString(fake));
    }

    @Test
    void courseByName() throws JsonProcessingException {
        final Set<Student> fakeStudents = Set.of(
                Student.builder().firstName("firstname1").lastName("lastname1").build(),
                Student.builder().firstName("firstname2").lastName("lastname2").build(),
                Student.builder().firstName("firstname3").lastName("lastname3").build(),
                Student.builder().firstName("firstname4").lastName("lastname4").build()
        );
        final Course fake = Course.builder()
                .name("name")
                .id(5)
                .students(fakeStudents)
                .build();

        when(courseServMock.getByName("name")).thenReturn(Optional.of(fake));

        graphQlTester
                .documentName("courseByName")
                .variable("name", "name")
                .execute()
                .path("courseByName")
                .matchesJson(objectMapper.writeValueAsString(fake));
    }

    @Test
    void courseByTeacherLastName() throws JsonProcessingException {

        final Set<TeacherHasDegree> degrees = Set.of(
                TeacherHasDegree.builder().degree(Degree.builder().name("degree1").build()).build(),
                TeacherHasDegree.builder().degree(Degree.builder().name("degree2").build()).build(),
                TeacherHasDegree.builder().degree(Degree.builder().name("degree3").build()).build()
        );

        final Teacher teacher = Teacher.builder().firstName("firstName")
                .lastName("lastName")
                .degrees(degrees)
                .build();

        final List<Course> courses = List.of(
                Course.builder().name("name1").id(1).teacher(teacher).build(),
                Course.builder().name("name2").id(2).teacher(teacher).build(),
                Course.builder().name("name3").id(3).teacher(teacher).build(),
                Course.builder().name("name4").id(4).teacher(teacher).build()
        );

        when(courseServMock.getByTeacherLastName("lastName")).thenReturn(courses);

        graphQlTester
                .documentName("courseByTeacherLastName")
                .variable("lastName", "lastName")
                .execute()
                .path("courseByTeacherLastName")
                .matchesJson(objectMapper.writeValueAsString(courses));


    }
}