package com.example.Spring.LMS.entitys;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import com.example.Spring.LMS.enums.CourseLevel;

@Entity
@Table(name = "course")
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

    public List<CommentEntity> getComments() {
        return comment;
    }

    public void setComments(List<CommentEntity> comment) {
        this.comment = comment;
    }

    public CourseEntity() {}

    public List<LessonEntity> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonEntity> lessons) {
        this.lessons = lessons;
    }

    public CourseEntity(String name, String description, String category, CourseLevel level) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EnrollmentEntity> getEnrollments() {
        return enrollment;
    }

    public void setEnrollments(List<EnrollmentEntity> enrollmentEntities) {
        this.enrollment = enrollmentEntities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CourseLevel getLevel() {
        return level;
    }

    public void setLevel(CourseLevel level) {
        this.level = level;
    }

    public UsersEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(UsersEntity teacher) {
        this.teacher = teacher;
    }
}
