package com.example.Spring.LMS.enrollments;

import com.example.Spring.LMS.course.courseDto.CourseResponse;
import com.example.Spring.LMS.users.usersDto.TeacherResponse;
import com.example.Spring.LMS.users.usersDto.UserResponse;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import com.example.Spring.LMS.lesson.LessonEntity;
import com.example.Spring.LMS.progresses.ProgressEntity;
import com.example.Spring.LMS.users.UsersEntity;
import com.example.Spring.LMS.enums.StatusOfProgress;
import com.example.Spring.LMS.enums.UserRole;
import com.example.Spring.LMS.enrollments.enrollmentsDto.Enrollment;
import com.example.Spring.LMS.course.CourseRepository;
import com.example.Spring.LMS.repositories.EnrollmentsRepository;
import com.example.Spring.LMS.repositories.ProgressesRepository;
import com.example.Spring.LMS.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentsRepository repository;

    private final UsersRepository usersRepository;

    private final CourseRepository courseRepository;

    private final ProgressesRepository progressesRepository;

    public EnrollmentService(EnrollmentsRepository repository, UsersRepository usersRepository, CourseRepository courseRepository,  ProgressesRepository progressesRepository) {
        this.repository = repository;
        this.usersRepository = usersRepository;
        this.courseRepository = courseRepository;
        this.progressesRepository = progressesRepository;
    }

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

        var newEnrollment = new EnrollmentEntity(
                user,
                course
        );

        for (LessonEntity lesson : course.getLessons()) {
            var newProgress = new ProgressEntity(
                    LocalDateTime.now(),
                    user,
                    lesson,
                    StatusOfProgress.FINISHED
            );
            progressesRepository.save(newProgress);
        }

        var saved = repository.save(newEnrollment);

        return toDomainEnrollment(saved);
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
            courses.add(toDomainCourse(enrollment));
        }

        return courses;
    }

    private void checkIfAStudent(UsersEntity user) {
        if (user.getRole() != UserRole.STUDENT) {
            throw new NoPermissionException("You are not allowed to perform this action!");
        }
    }

    private Enrollment toDomainEnrollment(EnrollmentEntity enrollmentEntity) {
        return new Enrollment(
                enrollmentEntity.getId(),
                enrollmentEntity.getDateEnrollment(),
                toDomainUser(enrollmentEntity.getStudent()),
                toDomainCourse(enrollmentEntity)
        );
    }

    private UserResponse toDomainUser(UsersEntity user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }

    private CourseResponse toDomainCourse(EnrollmentEntity enrollment) {
        return new CourseResponse(
                enrollment.getCourse().getId(),
                enrollment.getCourse().getName(),
                enrollment.getCourse().getDescription(),
                enrollment.getCourse().getCategory(),
                enrollment.getCourse().getLevel().name(),
                new TeacherResponse(
                        enrollment.getCourse().getTeacher().getUsername(),
                        enrollment.getCourse().getTeacher().getEmail()
                )
        );
    }
}
