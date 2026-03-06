package com.example.Spring.LMS.lesson;

import com.example.Spring.LMS.enrollments.EnrollmentsRepository;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import com.example.Spring.LMS.lesson.dto.LessonResponse;
import com.example.Spring.LMS.lesson.dto.LessonToCreate;
import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.course.CourseRepository;
import com.example.Spring.LMS.mapper.LessonMapper;
import com.example.Spring.LMS.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonsServiceImpl implements LessonsService {

    private final LessonMapper lessonMapper;

    private final LessonsRepository repository;

    private final CourseRepository courseRepository;

    private final EnrollmentsRepository enrollmentsRepository;

    private final UsersRepository usersRepository;

    @Override
    public void deleteLessonById(Long id, Long lessonId, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("There is no course with id " + id)
        );
        checkIfHasRights(course, userId);
        repository.deleteById(lessonId);
    }

    @Override
    public void editLessonById(Long id, Long lessonId, Long userId, LessonToCreate lessonToUpdate) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id " + id + " not found")
        );
        checkIfHasRights(course, userId);

        repository.findById(lessonId).orElseThrow(()
                -> new EntityNotFoundException("Lesson with id " + lessonId + " not found"));

        repository.updateLesson(
                lessonId,
                lessonToUpdate.name(),
                lessonToUpdate.content(),
                lessonToUpdate.videoUrl()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public LessonResponse getLessonById(Long id, Long lessonId, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id " + id + " not found"));

        var lesson = repository.findById(lessonId).orElseThrow(()
                -> new EntityNotFoundException("Lesson with id " + lessonId + " not found")
        );

        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User with id " + userId + " not found"));

        if (enrollmentsRepository.existsByStudentAndCourse(user, course)) {
            throw new NoPermissionException("You are not allowed to perform this action");
        }

        return lessonMapper.toResponse(lesson);
    }

    @Override
    public LessonResponse createLesson(Long id, LessonToCreate lesson, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("There is no course with with id " + id));
        checkIfHasRights(course, userId);

        if (!lesson.videoUrl().startsWith("https://")) {
            throw new IllegalStateException("You have to put real website");
        }

        var newLesson = LessonEntity.builder()
                .name(lesson.name())
                .content(lesson.content())
                .videoUrl(lesson.videoUrl())
                .course(course)
                .build();

        var saved = repository.save(newLesson);
        return lessonMapper.toResponse(saved);

    }

    @Transactional(readOnly = true)
    @Override
    public List<LessonResponse> getAllLessons(Long id, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("There is no course with id " + id)
        );

        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User with id " + userId + " not found"));

        if (enrollmentsRepository.existsByStudentAndCourse(user, course))
                throw new NoPermissionException("There is no enrolled lesson");

        List<LessonEntity> lessons = course.getLessons();

        return lessons.stream().map(lessonMapper::toResponse).toList();
    }

    private void checkIfHasRights(CourseEntity course, Long id) {
        if (!course.getTeacher().getId().equals(id)) {
            throw new NoPermissionException("Access denied");
        }
    }

}
