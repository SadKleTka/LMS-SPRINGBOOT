package com.example.Spring.LMS.entitys;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private LessonEntity lessonEntity;

    @OneToMany(mappedBy = "test", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<QuestionEntity> questionEntities = new ArrayList<>();

    public List<QuestionEntity> getQuestions() {
        return questionEntities;
    }
    public TestEntity() {}

    public TestEntity(String name, LessonEntity lessonEntity, List<QuestionEntity> questionEntities) {
        this.name = name;
        this.lessonEntity = lessonEntity;
        this.questionEntities = questionEntities;
    }

    public void setQuestions(List<QuestionEntity> questionEntities) {
        this.questionEntities = questionEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LessonEntity getLesson() {
        return lessonEntity;
    }

    public void setLesson(LessonEntity lessonEntity) {
        this.lessonEntity = lessonEntity;
    }
}
