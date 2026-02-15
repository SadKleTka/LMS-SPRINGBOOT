package com.example.Spring.LMS.services;

import com.example.Spring.LMS.DTO.CourseResponse;
import com.example.Spring.LMS.DTO.TeacherResponse;
import com.example.Spring.LMS.entitys.CourseEntity;
import com.example.Spring.LMS.records.Course;
import com.example.Spring.LMS.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {

    private final CourseRepository repository;

    public CoursesService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<CourseResponse> getAllCourses() {
        List<CourseEntity> courses = repository.findAll();
        return courses.stream().map(this::toResponse).toList();
    }

    private CourseResponse toResponse(CourseEntity c) {
        return new CourseResponse(
                c.getName(),
                c.getDescription(),
                c.getCategory(),
                c.getLevel().name(),
                new TeacherResponse(
                        c.getTeacher().getUsername(),
                        c.getTeacher().getEmail()
                )
        );
    }



    private Course toDomainReservation(CourseEntity course) {
        return new Course(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getCategory(),
                course.getLevel(),
                course.getTeacher(),
                course.getLessons(),
                course.getEnrollments(),
                course.getComments()
        );
    }
}
