package com.study.identity_service.mapper;

import com.study.identity_service.dto.request.UserCreationRequest;
import com.study.identity_service.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
}
