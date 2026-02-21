package com.example.Spring.LMS.services;

import com.example.Spring.LMS.CourseDTO.ProgressToResponse;
import com.example.Spring.LMS.entities.ProgressEntity;
import com.example.Spring.LMS.enums.StatusOfProgress;
import com.example.Spring.LMS.enums.UserRole;
import com.example.Spring.LMS.repositories.LessonsRepository;
import com.example.Spring.LMS.repositories.ProgressesRepository;
import com.example.Spring.LMS.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProgressService {

    private final LessonsRepository lessonsRepository;

    private final ProgressesRepository repository;

    private final UsersRepository usersRepository;

    public ProgressService(ProgressesRepository repository, UsersRepository usersRepository, LessonsRepository lessonsRepository) {
        this.repository = repository;
        this.usersRepository = usersRepository;
        this.lessonsRepository = lessonsRepository;
    }

    public void completeLesson(Long id, Long userId) {
        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        if (user.getRole() != UserRole.STUDENT) {
            throw new IllegalStateException("Only student can complete this lesson");
        }

        var lesson = lessonsRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Lesson not found"));

        var progresses = user.getStudentsProgress();
        byte count = 0;
        for (ProgressEntity progress : progresses) {
            if (!progress.getLesson().getId().equals(lesson.getId())) {
                count++;
            }
            if (count == progresses.size()) {
                throw new IllegalArgumentException("You are not enrolled to this lesson");
            }
        }

        var newProgress = new ProgressEntity(
                LocalDateTime.now(),
                user,
                lesson,
                StatusOfProgress.FINISHED
        );

        repository.save(newProgress);
    }

    public List<ProgressToResponse> getProgress(Long id) {
        var user = usersRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("User not found with id " + id));

        var progresses = user.getStudentsProgress();

        if (progresses.isEmpty()) {
            throw new IllegalStateException("Student has not enrolled to any courses");
        }

        return progresses.stream().map(this::toDomain).toList();
    }

    private ProgressToResponse toDomain(ProgressEntity entity) {
        return new ProgressToResponse(
                entity.getLesson().getName(),
                entity.getStudent().getUsername(),
                entity.getStatus(),
                entity.getCompletedAt()
        );
    }
}
