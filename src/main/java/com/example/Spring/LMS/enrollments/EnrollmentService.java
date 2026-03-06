package com.example.Spring.LMS.enrollments;

import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.enrollments.dto.Enrollment;

import java.util.List;

public interface EnrollmentService {

    Enrollment enrollToTheCourse(Long userId, Long courseId);

    List<CourseResponse> getStudentsCourses(Long userId);
}