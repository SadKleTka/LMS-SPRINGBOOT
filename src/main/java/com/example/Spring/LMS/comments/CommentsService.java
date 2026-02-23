package com.example.Spring.LMS.comments;

import com.example.Spring.LMS.comments.dto.CommentToCreate;
import com.example.Spring.LMS.comments.dto.CommentToResponse;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import com.example.Spring.LMS.course.CourseRepository;
import com.example.Spring.LMS.mapper.CommentMapper;
import com.example.Spring.LMS.users.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentMapper commentMapper;

    private final CommentsRepository repository;

    private final CourseRepository courseRepository;

    private final UsersRepository usersRepository;

    public CommentToResponse createComment(Long id, CommentToCreate comment, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("No course with id: " + id));

        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("No user with id: " + userId));

        var newComment = CommentEntity
                .builder()
                .text(comment.text())
                .user(user)
                .course(course)
                .build();

        var userEnrollments = user.getEnrollmentEntities();
        byte count = 0;
        for (var enrollment : userEnrollments) {
            if (!enrollment.getCourse().getId().equals(course.getId())) {
                count++;
            }
            if (count == userEnrollments.size()) {
                throw new NoPermissionException("You can`t leave a comment to not enrolled course");
            }
        }

        var saved = repository.save(newComment);

        return commentMapper.commentToResponse(saved);
    }


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
