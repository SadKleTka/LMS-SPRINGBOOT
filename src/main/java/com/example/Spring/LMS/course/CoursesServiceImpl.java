package com.example.Spring.LMS.course;

import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.course.dto.CourseToCreate;
import com.example.Spring.LMS.enums.UserRole;
import com.example.Spring.LMS.mapper.CourseMapper;
import com.example.Spring.LMS.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoursesServiceImpl implements CoursesService {

    private final CourseMapper courseMapper;

    private final CourseRepository repository;

    private final UsersRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CourseResponse> getAllCourses() {

        List<CourseEntity> courses = repository.findAll();

        return courses.stream().map(courseMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CourseResponse getCourseById(Long id) {
        var course = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id " + id + " not found")
        );

        return courseMapper.toResponse(course);
    }

    @Override
    public CourseToCreate createCourse(Long id, CourseToCreate course) {
        var user = userRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("User with id " + id + " not found")
        );

        var newCourse = CourseEntity.builder()
                .name(course.name())
                .description(course.description())
                .category(course.category())
                .level(course.level())
                .build();
        newCourse.setTeacher(user);

        var saved = repository.save(newCourse);
        return courseMapper.toCourseToCreate(saved);
    }

    @Override
    public void deleteCourseById(Long id, Long userId) {
        var course = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id " + id + " not found")
        );
        var user = userRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User with id " + userId + " not found")
        );

        if (user.getRole() == UserRole.TEACHER && !course.getTeacher().getId().equals(user.getId())) {
            throw new NoPermissionException("Teacher: " + user.getUsername() + " you can`t delete other's courses");
        }

        repository.delete(course);
    }

    @Override
    public void editCourseById(Long id, Long userId , CourseToCreate courseToUpdate) {
        var user = userRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User with id " + userId + " not found")
        );

        var course = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id " + id + " not found"));

        if (user.getRole() == UserRole.TEACHER && !course.getTeacher().getId().equals(user.getId())) {
            throw new NoPermissionException("Teacher: " + user.getName() + " you can`t edit other's courses");
        }

        repository.updateCourse(
                id,
                courseToUpdate.name(),
                courseToUpdate.description(),
                courseToUpdate.category(),
                courseToUpdate.level()
        );
    }



}
