package com.example.Spring.LMS.progresses;

import com.example.Spring.LMS.enums.StatusOfProgress;
import com.example.Spring.LMS.enums.UserRole;
import com.example.Spring.LMS.lesson.LessonsRepository;
import com.example.Spring.LMS.mapper.ProgressMapper;
import com.example.Spring.LMS.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressMapper progressMapper;

    private final LessonsRepository lessonsRepository;

    private final ProgressesRepository repository;

    private final UsersRepository usersRepository;

    public void completeLesson(Long id, Long userId) {
        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        if (user.getRole() != UserRole.STUDENT) {
            throw new IllegalStateException("Only student can complete this lesson");
        }

        var lesson = lessonsRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Lesson not found"));

        var progresses = user.getStudentsProgressEntities();
        byte count = 0;
        for (ProgressEntity progress : progresses) {
            if (!progress.getLessons().getId().equals(lesson.getId())) {
                count++;
            }
            if (count == progresses.size()) {
                throw new IllegalArgumentException("You are not enrolled to this lesson");
            }
        }

        var newProgress = ProgressEntity.builder()
                .completedAt(LocalDateTime.now())
                .student(user)
                .lessons(lesson)
                .status(StatusOfProgress.FINISHED)
                        .build();

        repository.save(newProgress);
    }

    public List<ProgressToResponse> getProgress(Long id) {
        var user = usersRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("User not found with id " + id));

        var progresses = user.getStudentsProgressEntities();

        if (progresses.isEmpty()) {
            throw new IllegalStateException("Student has not enrolled to any courses");
        }

        return progresses.stream().map(progressMapper::toResponse).toList();
    }

}
