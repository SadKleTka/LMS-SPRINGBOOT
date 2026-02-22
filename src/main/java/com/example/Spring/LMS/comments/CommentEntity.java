package com.example.Spring.LMS.comments;


import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.users.UsersEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private CourseEntity course;

}
