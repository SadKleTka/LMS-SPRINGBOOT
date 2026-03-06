package com.example.Spring.LMS.comments;

import com.example.Spring.LMS.comments.dto.CommentToCreate;
import com.example.Spring.LMS.comments.dto.CommentToResponse;
import com.example.Spring.LMS.course.CourseRepository;
import com.example.Spring.LMS.enrollments.EnrollmentsRepository;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import com.example.Spring.LMS.mapper.CommentMapper;
import com.example.Spring.LMS.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {

    private final CommentMapper commentMapper;

    private final CommentsRepository repository;

    private final CourseRepository courseRepository;

    private final UsersRepository usersRepository;

    private final EnrollmentsRepository enrollmentsRepository;

    @Override
    public CommentToResponse createComment(Long id, CommentToCreate comment, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("No course with id: " + id));

        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("No user with id: " + userId));

        if (!enrollmentsRepository.existsByStudentAndCourse(user, course)) {
            throw new NoPermissionException("You can`t leave a comment to not enrolled course");
        }

        var newComment = CommentEntity
                .builder()
                .text(comment.text())
                .user(user)
                .course(course)
                .build();

        var saved = repository.save(newComment);

        return commentMapper.commentToResponse(saved);
    }


    @Override
    public List<CommentToResponse> getComments(Long id) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("No course with id: " + id));

        var comments = repository.findAllComments(course);

        if (comments.isEmpty()) {
            throw new EntityNotFoundException("No comments found");
        }

        return comments.stream().map(commentMapper::commentToResponse).toList();

    }
}
