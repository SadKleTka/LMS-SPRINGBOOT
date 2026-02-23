package com.example.Spring.LMS.enrollments;

import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.mapper.EnrollmentMapper;
import com.example.Spring.LMS.users.dto.TeacherResponse;
import com.example.Spring.LMS.users.dto.UserResponse;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import com.example.Spring.LMS.lesson.LessonEntity;
import com.example.Spring.LMS.progresses.ProgressEntity;
import com.example.Spring.LMS.users.UsersEntity;
import com.example.Spring.LMS.enums.StatusOfProgress;
import com.example.Spring.LMS.enums.UserRole;
import com.example.Spring.LMS.enrollments.dto.Enrollment;
import com.example.Spring.LMS.course.CourseRepository;
import com.example.Spring.LMS.progresses.ProgressesRepository;
import com.example.Spring.LMS.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentMapper enrollmentMapper;

    private final EnrollmentsRepository repository;

    private final UsersRepository usersRepository;

    private final CourseRepository courseRepository;

    private final ProgressesRepository progressesRepository;


    public Enrollment enrollToTheCourse(Long userId, Long courseId) {
        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User not found!"));
        checkIfAStudent(user);

        var course = courseRepository.findById(courseId).orElseThrow(()
                -> new EntityNotFoundException("Course not found!"));

        var enrollments = user.getEnrollments();
        for (var enroll : enrollments) {
            if (enroll.getCourse().getId().equals(course.getId())) {
                throw new IllegalStateException("Course with id " + courseId + " has been already enrolled");
            }
        }

        var newEnrollment = EnrollmentEntity.builder()
                .student(user)
                .course(course)
                .build();

        for (LessonEntity lesson : course.getLessons()) {
            var newProgress = ProgressEntity.builder()
                    .completedAt(LocalDateTime.now())
                    .student(user)
                    .lessons(lesson)
                    .status(StatusOfProgress.FINISHED)
                    .build();
            progressesRepository.save(newProgress);
        }

        var saved = repository.save(newEnrollment);

        return enrollmentMapper.toResponse(saved);
    }

    public List<CourseResponse> getStudentsCourses(Long userId) {
        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User not found!"));

        var enrollments = user.getEnrollments();

        if (enrollments.isEmpty()) {
            throw new EntityNotFoundException("User has no enrolled courses!");
        }

        List<CourseResponse> courses = new ArrayList<>();
        for (var enrollment : enrollments) {
            courses.add(enrollmentMapper.toCourseResponse(enrollment));
        }

        return courses;
    }

    private void checkIfAStudent(UsersEntity user) {
        if (user.getRole() != UserRole.STUDENT) {
            throw new NoPermissionException("You are not allowed to perform this action!");
        }
    }
}
