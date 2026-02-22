package com.example.Spring.LMS.enrollments;


import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.users.UsersEntity;
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
    @Column(name = "date_enrollment", nullable = false)
    private LocalDateTime dateEnrollment;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private UsersEntity student;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private CourseEntity course;

    public EnrollmentEntity() {}

    public EnrollmentEntity(UsersEntity student, CourseEntity courseEntity) {
        this.student = student;
        this.course = courseEntity;
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
        return course;
    }

    public void setCourse(CourseEntity courseEntity) {
        this.course = courseEntity;
    }
}
