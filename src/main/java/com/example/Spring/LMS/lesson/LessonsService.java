package com.example.Spring.LMS.lesson;

import com.example.Spring.LMS.lesson.dto.LessonResponse;
import com.example.Spring.LMS.lesson.dto.LessonToCreate;

import java.util.List;

public interface LessonsService {

    void deleteLessonById(Long id, Long lessonId, Long userId);

    void editLessonById(Long id, Long lessonId, Long userId, LessonToCreate lessonToUpdate);

    LessonResponse getLessonById(Long id, Long lessonId, Long userId);

    LessonResponse createLesson(Long id, LessonToCreate lesson, Long userId);

    List<LessonResponse> getAllLessons(Long id, Long userId);
}