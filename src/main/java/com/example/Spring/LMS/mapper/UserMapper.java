package com.example.Spring.LMS.mapper;

import com.example.Spring.LMS.users.UsersEntity;
import com.example.Spring.LMS.users.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "name")
    UserResponse toResponse(UsersEntity user);
}
