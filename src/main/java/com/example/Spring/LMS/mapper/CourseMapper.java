package com.example.Spring.LMS.mapper;

import com.example.Spring.LMS.course.CourseEntity;
import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.course.dto.CourseToCreate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseResponse toResponse(CourseEntity course);

    CourseToCreate toCourseToCreate(CourseEntity course);
}
