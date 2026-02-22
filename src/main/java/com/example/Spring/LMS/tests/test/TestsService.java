package com.example.Spring.LMS.tests.test;

import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.course.CourseRepository;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import com.example.Spring.LMS.lesson.LessonEntity;
import com.example.Spring.LMS.lesson.LessonsRepository;
import com.example.Spring.LMS.lesson.lessonDto.LessonResponse;
import com.example.Spring.LMS.tests.answers.AnswerOptionEntity;
import com.example.Spring.LMS.tests.answers.AnswersRepository;
import com.example.Spring.LMS.tests.answers.answersDto.AnswersToAnswer;
import com.example.Spring.LMS.tests.questions.QuestionEntity;
import com.example.Spring.LMS.tests.questions.QuestionsRepository;
import com.example.Spring.LMS.tests.questions.questionDto.QuestionToAnswer;
import com.example.Spring.LMS.tests.questions.questionDto.QuestionToCreate;
import com.example.Spring.LMS.tests.test.testDto.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestsService {

    private final TestsRepository repository;

    private final CourseRepository courseRepository;

    private final LessonsRepository lessonsRepository;

    private final QuestionsRepository questionsRepository;

    private final AnswersRepository answersRepository;

    public TestsService(TestsRepository repository, CourseRepository courseRepository, LessonsRepository lessonsRepository, QuestionsRepository questionsRepository, AnswersRepository answersRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.lessonsRepository = lessonsRepository;
        this.questionsRepository = questionsRepository;
        this.answersRepository = answersRepository;
    }

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

        return toDomainAnswer(saved);
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

        var newQuestion = new QuestionEntity(
                question.name()
        );
        newQuestion.setTest(test);

        var saved = questionsRepository.save(newQuestion);

        return toDomainQuestion(saved);
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

        var newTest = new TestEntity(
                testToCreate.name()
        );
        newTest.setLessons(lesson);

        var saved = repository.save(newTest);

        return toDomainTest(saved);
    }

    public Test getTest(Long lessonId) {
        var lesson = lessonsRepository.findById(lessonId).orElseThrow(()
                -> new EntityNotFoundException("Lesson with id: " + lessonId + " has no tests!"));

        TestEntity test = lesson.getTest();
        if (test == null) {
            throw new EntityNotFoundException("Lesson with id: " + lesson.getId() + " has no tests!");
        }

        return toDomainTest(test);
    }


    private void checkIfHasRights(CourseEntity course, Long id) {
        if (!course.getTeacher().getId().equals(id)) {
            throw new NoPermissionException("Access denied");
        }
    }

    private Test toDomainTest(TestEntity test) {
        return new Test(
                test.getName(),
                toDomainLesson(test.getLessons()),
                toDomainQuestions(test.getQuestions().stream().toList())
        );
    }

    private LessonResponse toDomainLesson(LessonEntity lessonEntity) {
        return new LessonResponse(
                lessonEntity.getId(),
                lessonEntity.getName(),
                lessonEntity.getContent(),
                lessonEntity.getVideoUrl()
        );
    }

    private List<QuestionToAnswer> toDomainQuestions(List<QuestionEntity> questions) {
        List<QuestionToAnswer> questionToAnswers = new ArrayList<>();
        for (QuestionEntity question : questions) {
            questionToAnswers.add(new QuestionToAnswer(
                    question.getId(),
                    question.getText(),
                    toDomainAnswers(question.getAnswers())
            ));
        }
        return questionToAnswers;
    }

    private QuestionToCreate toDomainQuestion(QuestionEntity questionEntity) {
        return new QuestionToCreate(
                questionEntity.getText()
        );
    }

    private List<AnswersToAnswer> toDomainAnswers(List<AnswerOptionEntity> answers) {
        List<AnswersToAnswer> answersToAnswers = new ArrayList<>();
        for (AnswerOptionEntity answerOption : answers) {
            answersToAnswers.add(new AnswersToAnswer(
                    answerOption.getText(),
                    answerOption.getCorrect()
            ));
        }
        return answersToAnswers;
    }

    private AnswersToAnswer toDomainAnswer(AnswerOptionEntity answerOptionEntity) {
        return new AnswersToAnswer(
                answerOptionEntity.getText(),
                answerOptionEntity.getCorrect()
        );
    }

    private static AnswerOptionEntity getAnswerOptionEntity(AnswersToAnswer answer, QuestionEntity question) {
        List<AnswerOptionEntity> answers = question.getAnswers();
        if (answers.size() == 4) {
            throw new IllegalStateException("Question cannot have more than 4 answers!");
        }

        var newAnswer = new AnswerOptionEntity(
                answer.text(),
                answer.isCorrect()
        );
        newAnswer.setQuestion(question);

        if (!answers.isEmpty()) {
            for (AnswerOptionEntity answerOptionEntity : answers) {
                if (answerOptionEntity.getCorrect()) {
                    if (newAnswer.getCorrect()) {
                        throw new IllegalStateException("Question cannot have more than 1 right answers!");
                    }
                }
            }
        }
        return newAnswer;
    }
}
