package com.example.Spring.LMS.entitys;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private CourseEntity courseEntity;

    public CommentEntity() {}

    public CommentEntity(String text, UsersEntity student, CourseEntity courseEntity) {
        this.text = text;
        this.user = student;
        this.courseEntity = courseEntity;
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity student) {
        this.user = student;
    }

    public CourseEntity getCourse() {
        return courseEntity;
    }

    public void setCourse(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    @Override
    public String toString() {
        return getText();
    }
}
