package com.example.Spring.LMS.services;

import com.example.Spring.LMS.CourseDTO.CourseResponse;
import com.example.Spring.LMS.CourseDTO.CourseToCreate;
import com.example.Spring.LMS.DTO.TeacherResponse;
import com.example.Spring.LMS.entitys.CourseEntity;
import com.example.Spring.LMS.entitys.UsersEntity;
import com.example.Spring.LMS.enums.UserRole;
import com.example.Spring.LMS.records.Course;
import com.example.Spring.LMS.repositories.CourseRepository;
import com.example.Spring.LMS.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.Spring.LMS.Exceptions.NoPermissionException;

import java.util.List;

@Service
public class CoursesService {

    private final CourseRepository repository;

    private final UsersRepository userRepository;

    public CoursesService(CourseRepository repository, UsersRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<CourseResponse> getAllCourses() {

        List<CourseEntity> courses = repository.findAll();

        return courses.stream().map(this::toResponse).toList();
    }

    public CourseResponse getCourseById(Long id) {
        var course = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id " + id + " not found")
        );

        return toResponse(course);
    }

    public CourseToCreate createCourse(Long id, CourseToCreate course) {
        var user = userRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("User with id " + id + " not found")
        );
        checkIfTeacher(user);

        var newCourse = new CourseEntity(
                course.name(),
                course.description(),
                course.category(),
                course.level()
        );
        newCourse.setTeacher(user);

        var saved = repository.save(newCourse);
        return toCreateCourse(saved);
    }

    public void deleteCourseById(Long id, Long userId) {
        var course = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id " + id + " not found")
        );
        var user = userRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User with id " + userId + " not found")
        );
        checkIfTeacher(user);

        if (user.getRole().equals(UserRole.TEACHER) && !course.getTeacher().getId().equals(user.getId())) {
            throw new NoPermissionException("Teacher: " + user.getUsername() + " you can`t delete other's courses");
        }

        repository.delete(course);
    }

    @Transactional
    public void editCourseById(Long id, Long userId , CourseToCreate courseToUpdate) {
        var user = userRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User with id " + userId + " not found")
        );
        checkIfTeacher(user);

        repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id " + id + " not found"));

        repository.updateCourse(
                id,
                courseToUpdate.name(),
                courseToUpdate.description(),
                courseToUpdate.category(),
                courseToUpdate.level()
        );
    }

    private void checkIfTeacher(UsersEntity user) {
        if (user.getRole().equals(UserRole.STUDENT)) {
            throw new NoPermissionException("Student can`t delete courses");
        }
    }

    private CourseToCreate toCreateCourse(CourseEntity course) {
        return new CourseToCreate(
                course.getName(),
                course.getDescription(),
                course.getCategory(),
                course.getLevel()
        );
    }

    private CourseResponse toResponse(CourseEntity c) {
        return new CourseResponse(
                c.getId(),
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



    private Course toDomainCourse(CourseEntity course) {
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
