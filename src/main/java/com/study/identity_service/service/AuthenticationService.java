package com.study.identity_service.service;

import com.study.identity_service.dto.request.AuthenticationRequest;
import com.study.identity_service.exception.AppException;
import com.study.identity_service.exception.ErrorCode;
import com.study.identity_service.repository.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepo userRepo;

    public boolean authenticate(AuthenticationRequest request){
        var user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() ->new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches((request.getPassword()), user.getPassword());
    }
}
