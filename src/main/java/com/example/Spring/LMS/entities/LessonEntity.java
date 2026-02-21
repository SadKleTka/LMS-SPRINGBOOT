package com.example.Spring.LMS.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lesson")
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

    public List<ProgressEntity> getStudentsProgress() {
        return studentsProgress;
    }

    public void setStudentsProgress(List<ProgressEntity> studentsProgressEntities) {
        this.studentsProgress = studentsProgressEntities;
    }

    public LessonEntity() {}

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    public LessonEntity(String name, String content, String videoUrl, CourseEntity courseEntity) {
        this.name = name;
        this.content = content;
        this.videoUrl = videoUrl;
        this.course = courseEntity;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity courseEntity) {
        this.course = courseEntity;
    }
}
