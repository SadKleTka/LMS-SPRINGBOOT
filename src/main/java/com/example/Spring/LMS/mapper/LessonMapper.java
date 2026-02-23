package com.example.Spring.LMS.mapper;

import com.example.Spring.LMS.lesson.LessonEntity;
import com.example.Spring.LMS.lesson.dto.LessonResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonResponse toResponse(LessonEntity lessonEntity);
}
