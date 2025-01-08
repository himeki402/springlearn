package com.study.identity_service.mapper;

import com.study.identity_service.dto.request.UserCreationRequest;
import com.study.identity_service.dto.request.UserUpdateRequest;
import com.study.identity_service.dto.response.UserResponse;
import com.study.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    UserResponse toUserResponse(User user);
}
