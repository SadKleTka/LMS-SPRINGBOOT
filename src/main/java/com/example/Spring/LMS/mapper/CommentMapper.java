package com.example.Spring.LMS.mapper;

import com.example.Spring.LMS.comments.CommentEntity;
import com.example.Spring.LMS.comments.dto.CommentToResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "name", expression = "java(comment.getUser().getName())")
    @Mapping(source = "createdAt", target = "time")
    CommentToResponse commentToResponse(CommentEntity comment);

}
