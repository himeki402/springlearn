package com.study.identity_service.service;

import com.study.identity_service.dto.request.UserCreationRequest;
import com.study.identity_service.dto.request.UserUpdateRequest;
import com.study.identity_service.entity.User;
import com.study.identity_service.exception.AppException;
import com.study.identity_service.exception.ErrorCode;
import com.study.identity_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User createUser(UserCreationRequest request){
        User user = new User();

        if (userRepo.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return userRepo.save(user);
    }

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User getUserById(String id){
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request){
        User user = getUserById(userId);

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepo.save(user);
    }

    public void deleteUser(String userId){
        userRepo.deleteById(userId);
    }
}
