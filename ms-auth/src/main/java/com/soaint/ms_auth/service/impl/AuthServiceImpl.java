package com.soaint.ms_auth.service.impl;

import com.soaint.ms_auth.dto.LoginRequest;
import com.soaint.ms_auth.dto.LoginResponse;
import com.soaint.ms_auth.exception.UserInvalidException;
import com.soaint.ms_auth.model.UserAuth;
import com.soaint.ms_auth.repository.UserAuthRepository;
import com.soaint.ms_auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserAuthRepository userAuthRepository;

    public AuthServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws UserInvalidException {
        log.debug("Login user {}", loginRequest.username());
        UserAuth userAuthOptional = userAuthRepository.findByUsernameAndPassword(loginRequest.username(), loginRequest.password())
                .orElseThrow(() -> new UserInvalidException("Invalid username or password"));
        // TODO Create JWT token and return it in the response
        return new LoginResponse("token");
    }
}
