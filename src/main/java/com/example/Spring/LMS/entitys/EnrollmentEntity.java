package com.example.Spring.LMS.entitys;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollment")
public class EnrollmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "date_enrollment")
    private LocalDateTime dateEnrollment;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private UsersEntity student;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private CourseEntity courseEntity;

    public EnrollmentEntity() {}

    public EnrollmentEntity(UsersEntity student, CourseEntity courseEntity) {
        this.student = student;
        this.courseEntity = courseEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateEnrollment() {
        return dateEnrollment;
    }

    public void setDateEnrollment(LocalDateTime dateEnrollment) {
        this.dateEnrollment = dateEnrollment;
    }

    public UsersEntity getStudent() {
        return student;
    }

    public void setStudent(UsersEntity student) {
        this.student = student;
    }

    public CourseEntity getCourse() {
        return courseEntity;
    }

    public void setCourse(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }
}
