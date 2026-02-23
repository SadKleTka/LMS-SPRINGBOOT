package com.example.Spring.LMS.tests.test;

import com.example.Spring.LMS.tests.questions.QuestionEntity;
import com.example.Spring.LMS.lesson.LessonEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "test")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id", nullable = false)
    private LessonEntity lessons;

    @OneToMany(mappedBy = "test", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<QuestionEntity> questions = new ArrayList<>();

}
