package com.example.Spring.LMS.lesson;

import com.example.Spring.LMS.exceptions.NoPermissionException;
import com.example.Spring.LMS.lesson.dto.LessonResponse;
import com.example.Spring.LMS.lesson.dto.LessonToCreate;
import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.course.CourseRepository;
import com.example.Spring.LMS.mapper.LessonMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonsService {

    private final LessonMapper lessonMapper;

    private final LessonsRepository repository;

    private final CourseRepository courseRepository;

    public void deleteLessonById(Long id, Long lessonId, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("There is no course with id " + id)
        );
        checkIfHasRights(course, userId);
        repository.deleteById(lessonId);
    }

    @Transactional
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

    public LessonResponse getLessonById(Long id, Long lessonId, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id " + id + " not found"));

        var lesson = repository.findById(lessonId).orElseThrow(()
                -> new EntityNotFoundException("Lesson with id " + id + " not found")
        );

        checkIfHasRights(course, userId);

        return lessonMapper.toResponse(lesson);
    }

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

    public List<LessonResponse> getAllLessons(Long id, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("There is no course with id " + id)
        );
        checkIfHasRights(course, userId);

        List<LessonEntity> lessons = course.getLessons();

        return lessons.stream().map(lessonMapper::toResponse).toList();
    }

    private void checkIfHasRights(CourseEntity course, Long id) {
        if (!course.getTeacher().getId().equals(id)) {
            throw new NoPermissionException("Access denied");
        }
    }

}
