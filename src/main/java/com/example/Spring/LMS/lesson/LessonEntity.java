package com.example.Spring.LMS.lesson;


import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.progresses.ProgressEntity;
import com.example.Spring.LMS.tests.test.TestEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lesson")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private CourseEntity course;

    @OneToOne(mappedBy = "lessons", orphanRemoval = true, cascade = CascadeType.ALL)
    private TestEntity test;

    @OneToMany(mappedBy = "lessons", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProgressEntity> studentsProgress = new ArrayList<>();

}
