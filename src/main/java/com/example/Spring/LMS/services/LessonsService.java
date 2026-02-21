package com.example.Spring.LMS.services;

import com.example.Spring.LMS.Exceptions.NoPermissionException;
import com.example.Spring.LMS.LessonDTO.LessonResponse;
import com.example.Spring.LMS.LessonDTO.LessonToCreate;
import com.example.Spring.LMS.entities.CourseEntity;
import com.example.Spring.LMS.entities.LessonEntity;
import com.example.Spring.LMS.repositories.CourseRepository;
import com.example.Spring.LMS.repositories.LessonsRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonsService {

    private final LessonsRepository repository;

    private final CourseRepository courseRepository;

    public LessonsService(LessonsRepository repository, CourseRepository courseRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

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

        repository.findById(id).orElseThrow(()
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

        return toDomainLesson(lesson);
    }

    public LessonResponse createLesson(Long id, LessonToCreate lesson, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("There is no course with with id " + id));
        checkIfHasRights(course, userId);

        if (!lesson.videoUrl().startsWith("https://")) {
            throw new IllegalStateException("You have to put real website");
        }

        var newLesson = new LessonEntity(
                lesson.name(),
                lesson.content(),
                lesson.videoUrl(),
                course
        );

        var saved = repository.save(newLesson);
        return toDomainLesson(saved);

    }

    public List<LessonResponse> getAllLessons(Long id, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("There is no course with id " + id)
        );
        checkIfHasRights(course, userId);

        List<LessonEntity> lessons = course.getLessons();

        return lessons.stream().map(this::toDomainLesson).toList();
    }

    private void checkIfHasRights(CourseEntity course, Long id) {
        if (!course.getTeacher().getId().equals(id)) {
            throw new NoPermissionException("Access denied");
        }
    }

    private LessonResponse toDomainLesson(LessonEntity lessonEntity) {
        return new LessonResponse(
                lessonEntity.getId(),
                lessonEntity.getName(),
                lessonEntity.getContent(),
                lessonEntity.getVideoUrl()
        );
    }
}
