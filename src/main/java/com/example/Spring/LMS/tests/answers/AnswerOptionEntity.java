package com.example.Spring.LMS.tests.answers;


import com.example.Spring.LMS.tests.questions.QuestionEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "answer_option")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerOptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private QuestionEntity question;

}
