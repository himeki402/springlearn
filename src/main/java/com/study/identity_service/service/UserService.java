package com.study.identity_service.service;

import com.study.identity_service.dto.request.UserCreationRequest;
import com.study.identity_service.dto.request.UserUpdateRequest;
import com.study.identity_service.dto.response.UserResponse;
import com.study.identity_service.entity.User;
import com.study.identity_service.enums.Role;
import com.study.identity_service.exception.AppException;
import com.study.identity_service.exception.ErrorCode;
import com.study.identity_service.mapper.UserMapper;
import com.study.identity_service.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserCreationRequest request){

        if (userRepo.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);

        return userRepo.save(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers(){
        log.info("dadadada");
        return userRepo.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUserById(String id){
        return userMapper.toUserResponse(userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepo.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepo.save(user));
    }

    public void deleteUser(String userId){
        userRepo.deleteById(userId);
    }
}
