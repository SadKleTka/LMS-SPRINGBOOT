package com.example.Spring.LMS.mapper;

import com.example.Spring.LMS.course.dto.CourseResponse;
import com.example.Spring.LMS.enrollments.EnrollmentEntity;
import com.example.Spring.LMS.enrollments.dto.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {

    @Mapping(source = "student.username", target = "student.name")
    Enrollment toResponse(EnrollmentEntity enrollmentEntity);

    @Mappings({
            @Mapping(source = "student.username", target = "name"),
            @Mapping(source = "course.description", target = "description"),
            @Mapping(source = "course.category", target = "category"),
            @Mapping(source = "course.level", target = "level"),
            @Mapping(source = "course.teacher", target = "teacher")
    })
    CourseResponse toCourseResponse(EnrollmentEntity enrollments);
}
