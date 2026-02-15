package com.example.Spring.LMS.entitys;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lesson")
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private CourseEntity courseEntity;

    @OneToOne(mappedBy = "lesson", orphanRemoval = true, cascade = CascadeType.ALL)
    private TestEntity test;

    @OneToMany(mappedBy = "lesson", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ProgressEntity> studentsProgressEntities = new ArrayList<>();

    public List<ProgressEntity> getStudentsProgress() {
        return studentsProgressEntities;
    }

    public void setStudentsProgress(List<ProgressEntity> studentsProgressEntities) {
        this.studentsProgressEntities = studentsProgressEntities;
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
        this.courseEntity = courseEntity;
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
        return courseEntity;
    }

    public void setCourse(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }
}
