package com.example.Spring.LMS.progresses;


import com.example.Spring.LMS.lesson.LessonEntity;
import com.example.Spring.LMS.users.UsersEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import com.example.Spring.LMS.enums.StatusOfProgress;

import java.time.LocalDateTime;

@Entity
@Table(name = "progress")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusOfProgress status;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private UsersEntity student;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id", nullable = false)
    private LessonEntity lessons;

}
