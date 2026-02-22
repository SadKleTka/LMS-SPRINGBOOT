package com.example.Spring.LMS.tests.questions;


import com.example.Spring.LMS.tests.answers.AnswerOptionEntity;
import com.example.Spring.LMS.tests.test.TestEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id", nullable = false)
    private TestEntity test;

    @OneToMany(mappedBy = "question", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<AnswerOptionEntity> answers = new ArrayList<>();

    public QuestionEntity() {}
    public QuestionEntity(String text) {
        this.text = text;
        this.test = test;
    }

    public Long getId() {
        return id;
    }

    public List<AnswerOptionEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerOptionEntity> answers) {
        this.answers = answers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }
}
