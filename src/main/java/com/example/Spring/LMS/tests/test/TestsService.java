package com.example.Spring.LMS.tests.test;

import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.course.CourseRepository;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import com.example.Spring.LMS.lesson.LessonsRepository;
import com.example.Spring.LMS.mapper.TestMapper;
import com.example.Spring.LMS.tests.answers.AnswerOptionEntity;
import com.example.Spring.LMS.tests.answers.AnswersRepository;
import com.example.Spring.LMS.tests.answers.dto.AnswersToAnswer;
import com.example.Spring.LMS.tests.questions.QuestionEntity;
import com.example.Spring.LMS.tests.questions.QuestionsRepository;
import com.example.Spring.LMS.tests.questions.dto.QuestionToCreate;
import com.example.Spring.LMS.tests.test.dto.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestsService {

    private final TestMapper testMapper;

    private final TestsRepository repository;

    private final CourseRepository courseRepository;

    private final LessonsRepository lessonsRepository;

    private final QuestionsRepository questionsRepository;

    private final AnswersRepository answersRepository;


    public void deleteQuestion(Long id, Long lessonId, Long userId, Long questionId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id - " + id + " not found!"));
        checkIfHasRights(course, userId);

        var lesson = lessonsRepository.findById(lessonId).orElseThrow(()
                -> new EntityNotFoundException("Lesson with id: " + lessonId + " not found!"));

        var question = questionsRepository.findById(questionId).orElseThrow(()
                -> new EntityNotFoundException("Question not found!"));

        if (!lesson.getTest().getQuestions().contains(question)) {
            throw new IllegalStateException("Question with id - " + questionId + " not found in the lesson");
        }
        lesson.getTest().getQuestions().remove(question);
        questionsRepository.delete(question);
    }

    public AnswersToAnswer createAnswer(Long id, Long lessonId, Long userId, Long questionId, AnswersToAnswer answer) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id - " + id + " not found!"));
        checkIfHasRights(course, userId);

        var lesson = lessonsRepository.findById(lessonId).orElseThrow(()
                -> new EntityNotFoundException("Lesson with id: " + lessonId + " not found!"));

        var test = lesson.getTest();

        if (test == null) {
            throw new IllegalStateException("Lesson doesn't have a test!");
        }

        var question = questionsRepository.findById(questionId).orElseThrow(()
                -> new EntityNotFoundException("Question not found!"));

        var newAnswer = getAnswerOptionEntity(answer, question);

        var saved = answersRepository.save(newAnswer);

        return testMapper.toAnswerResponse(saved);
    }

    public QuestionToCreate createQuestion(Long id, Long lessonId, QuestionToCreate question, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id " + id + " not found!"));
        checkIfHasRights(course, userId);

        var lesson = lessonsRepository.findById(lessonId).orElseThrow(()
                -> new EntityNotFoundException("Lesson with id: " + lessonId + " not found!"));

        var test = lesson.getTest();

        if (test == null) {
            throw new IllegalStateException("Lesson doesn't have a test!");
        }

        var newQuestion = QuestionEntity.builder()
                .text(question.name())
                .test(test)
                .build();

        var saved = questionsRepository.save(newQuestion);

        return testMapper.toQuestionResponse(saved);
    }

    @Transactional
    public void deleteTest(Long id, Long lessonId, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id: " + id + " not found!"));
        checkIfHasRights(course, userId);

        var lesson = lessonsRepository.findById(lessonId).orElseThrow(()
                -> new EntityNotFoundException("Lesson with id: " + lessonId + " not found!"));

        var test = lesson.getTest();

        if (test == null) {
            throw new IllegalStateException("Lesson doesn't have a test!");
        }

        repository.deleteTestByLesson(lesson);
    }

    public Test createTest(TestToCreate testToCreate, Long id, Long lessonId, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Course with id: " + id + " not found!"));
        checkIfHasRights(course, userId);

        var lesson = lessonsRepository.findById(lessonId).orElseThrow(()
                -> new EntityNotFoundException("Lesson with id: " + lessonId + " not found!"));

        var test = lesson.getTest();

        if (test != null) {
            throw new IllegalStateException("Test already exists!");
        }

        var newTest = TestEntity.builder()
                .name(testToCreate.name())
                .lessons(lesson)
                .build();

        var saved = repository.save(newTest);

        return testMapper.toResponse(saved);
    }

    public Test getTest(Long lessonId) {
        var lesson = lessonsRepository.findById(lessonId).orElseThrow(()
                -> new EntityNotFoundException("Lesson with id: " + lessonId + " has no tests!"));

        TestEntity test = lesson.getTest();
        if (test == null) {
            throw new EntityNotFoundException("Lesson with id: " + lesson.getId() + " has no tests!");
        }

        return testMapper.toResponse(test);
    }


    private void checkIfHasRights(CourseEntity course, Long id) {
        if (!course.getTeacher().getId().equals(id)) {
            throw new NoPermissionException("Access denied");
        }
    }

    private static AnswerOptionEntity getAnswerOptionEntity(AnswersToAnswer answer, QuestionEntity question) {
        List<AnswerOptionEntity> answers = question.getAnswers();
        if (answers.size() == 4) {
            throw new IllegalStateException("Question cannot have more than 4 answers!");
        }

        var newAnswer = AnswerOptionEntity.builder()
                .text(answer.text())
                .isCorrect(answer.isCorrect())
                .question(question)
                .build();

        if (!answers.isEmpty()) {
            for (AnswerOptionEntity answerOptionEntity : answers) {
                if (answerOptionEntity.getIsCorrect()) {
                    if (newAnswer.getIsCorrect()) {
                        throw new IllegalStateException("Question cannot have more than 1 right answers!");
                    }
                }
            }
        }
        return newAnswer;
    }
}
