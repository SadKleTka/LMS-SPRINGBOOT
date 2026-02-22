package com.example.Spring.LMS.mapper;

import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.course.dto.CourseToCreate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseResponse toResponse(CourseEntity course);

    CourseToCreate toCourseToCreate(CourseEntity course);
}
