package com.example.Spring.LMS.services;

import com.example.Spring.LMS.dto.courseDto.CommentToCreate;
import com.example.Spring.LMS.dto.courseDto.CommentToResponse;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import com.example.Spring.LMS.entities.CommentEntity;
import com.example.Spring.LMS.repositories.CommentsRepository;
import com.example.Spring.LMS.repositories.CourseRepository;
import com.example.Spring.LMS.repositories.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {

    private final CommentsRepository repository;

    private final CourseRepository courseRepository;

    private final UsersRepository usersRepository;

    public CommentsService(CommentsRepository repository, CourseRepository courseRepository, UsersRepository usersRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.usersRepository = usersRepository;
    }

    public CommentToResponse createComment(Long id, CommentToCreate comment, Long userId) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("No course with id: " + id));

        var user = usersRepository.findById(userId).orElseThrow(()
                -> new EntityNotFoundException("No user with id: " + userId));

        var newComment = new CommentEntity(
                comment.text(),
                user,
                course
        );

        var userEnrollments = user.getEnrollments();
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

        return toDomainComment(saved);
    }


    public List<CommentToResponse> getComments(Long id) {
        var course = courseRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("No course with id: " + id));

        var comments = repository.findAllComments(course);

        if (comments.isEmpty()) {
            throw new EntityNotFoundException("No comments found");
        }

        return comments.stream().map(this::toDomainComment).toList();

    }

    private CommentToResponse toDomainComment(CommentEntity comment) {
        return new CommentToResponse(
                comment.getUser().getUsername(),
                comment.getText(),
                comment.getCreatedAt()
        );
    }
}
