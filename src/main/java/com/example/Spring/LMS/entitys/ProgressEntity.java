package com.example.Spring.LMS.entitys;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.example.Spring.LMS.enums.StatusOfProgress;

import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
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

    public ProgressEntity(LocalDateTime completedAt, UsersEntity student, LessonEntity lessonEntity, StatusOfProgress status) {
        this.completedAt = completedAt;
        this.student = student;
        this.lessons = lessonEntity;
        this.status = status;
    }

    public StatusOfProgress getStatus() {
        return status;
    }

    public void setStatus(StatusOfProgress status) {
        this.status = status;
    }

    public ProgressEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public UsersEntity getStudent() {
        return student;
    }

    public void setStudent(UsersEntity student) {
        this.student = student;
    }

    public LessonEntity getLesson() {
        return lessons;
    }

    public void setLesson(LessonEntity lessonEntity) {
        this.lessons = lessonEntity;
    }
}
