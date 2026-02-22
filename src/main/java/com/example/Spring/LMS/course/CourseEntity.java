package com.example.Spring.LMS.course;

import com.example.Spring.LMS.comments.CommentEntity;
import com.example.Spring.LMS.enrollments.EnrollmentEntity;
import com.example.Spring.LMS.lesson.LessonEntity;
import com.example.Spring.LMS.users.UsersEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import com.example.Spring.LMS.enums.CourseLevel;
import lombok.*;

@Entity
@Table(name = "course")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private CourseLevel level;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    private UsersEntity teacher;

    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<LessonEntity> lessons = new ArrayList<>();

    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<EnrollmentEntity> enrollment = new ArrayList<>();

    @OneToMany(mappedBy = "course", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CommentEntity> comment = new ArrayList<>();

}
