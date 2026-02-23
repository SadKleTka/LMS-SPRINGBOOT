package com.example.Spring.LMS.mapper;

import com.example.Spring.LMS.progresses.ProgressEntity;
import com.example.Spring.LMS.progresses.ProgressToResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProgressMapper {

    @Mappings({
            @Mapping(source = "lessons.name", target = "lessonName"),
            @Mapping(source = "student.username", target = "name"),
            @Mapping(source = "completedAt", target = "date")
    })
    ProgressToResponse toResponse(ProgressEntity entity);
}
