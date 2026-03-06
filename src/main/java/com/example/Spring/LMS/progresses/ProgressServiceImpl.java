package com.example.Spring.LMS.progresses;

import com.example.Spring.LMS.enrollments.EnrollmentsRepository;
import com.example.Spring.LMS.enums.StatusOfProgress;
import com.example.Spring.LMS.enums.UserRole;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import com.example.Spring.LMS.lesson.LessonsRepository;
import com.example.Spring.LMS.mapper.ProgressMapper;
import com.example.Spring.LMS.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProgressServiceImpl implements ProgressService {

    private final ProgressMapper progressMapper;

    private final LessonsRepository lessonsRepository;

    private final ProgressesRepository repository;

    private final UsersRepository usersRepository;

    private final EnrollmentsRepository enrollmentsRepository;

    @Override
    public void completeLesson(Long id, Long userId) {
        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("User not found"));

        if (user.getRole() != UserRole.STUDENT) {
            throw new IllegalStateException("Only student can complete this lesson");
        }

        var lesson = lessonsRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Lesson not found"));

        if (!enrollmentsRepository.existsByStudentAndCourse(user, lesson.getCourse())) {
            throw new NoPermissionException("You have no permission to complete this lesson");
        }

        var progress = repository.findByStudentAndStatusAndLesson(user, StatusOfProgress.STARTED, lesson).orElseThrow(()
                -> new IllegalStateException("Lesson has been already completed"));

        repository.finishLesson(progress.getId(), StatusOfProgress.FINISHED);
    }

    @Transactional(readOnly = true)
    @Override
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
