package com.example.Spring.LMS.enrollments;

import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.mapper.EnrollmentMapper;
import com.example.Spring.LMS.lesson.LessonEntity;
import com.example.Spring.LMS.progresses.ProgressEntity;
import com.example.Spring.LMS.enums.StatusOfProgress;
import com.example.Spring.LMS.enrollments.dto.Enrollment;
import com.example.Spring.LMS.course.CourseRepository;
import com.example.Spring.LMS.progresses.ProgressesRepository;
import com.example.Spring.LMS.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentMapper enrollmentMapper;

    private final EnrollmentsRepository repository;

    private final UsersRepository usersRepository;

    private final CourseRepository courseRepository;

    private final ProgressesRepository progressesRepository;

    @Override
    public Enrollment enrollToTheCourse(Long userId, Long courseId) {
        long start = System.nanoTime();
        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User not found!"));

        long end = System.nanoTime();
        log.info("find a user took {}", end - start);
        var course = courseRepository.findById(courseId).orElseThrow(()
                -> new EntityNotFoundException("Course not found!"));

        var enrollments = user.getEnrollmentEntities();
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
                    .status(StatusOfProgress.STARTED)
                    .build();
            progressesRepository.save(newProgress);
        }

        var saved = repository.save(newEnrollment);

        return enrollmentMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CourseResponse> getStudentsCourses(Long userId) {
        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User not found!"));

        var enrollments = user.getEnrollmentEntities();

        if (enrollments.isEmpty()) {
            throw new EntityNotFoundException("User has no enrolled courses!");
        }

        List<CourseResponse> courses = new ArrayList<>();
        for (var enrollment : enrollments) {
            courses.add(enrollmentMapper.toCourseResponse(enrollment));
        }

        return courses;
    }

}
