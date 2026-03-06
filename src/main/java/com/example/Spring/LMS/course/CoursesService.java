package com.example.Spring.LMS.course;

import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.course.dto.CourseToCreate;

import java.util.List;

public interface CoursesService {

    List<CourseResponse> getAllCourses();

    CourseResponse getCourseById(Long id);

    CourseToCreate createCourse(Long id, CourseToCreate course);

    void deleteCourseById(Long id, Long userId);

    void editCourseById(Long id, Long userId , CourseToCreate courseToUpdate);
}