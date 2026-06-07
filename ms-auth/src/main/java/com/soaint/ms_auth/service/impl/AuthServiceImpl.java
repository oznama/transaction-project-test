package com.soaint.ms_auth.service.impl;

import com.soaint.ms_auth.dto.LoginRequest;
import com.soaint.ms_auth.dto.LoginResponse;
import com.soaint.ms_auth.exception.UserInvalidException;
import com.soaint.ms_auth.model.UserAuth;
import com.soaint.ms_auth.repository.UserAuthRepository;
import com.soaint.ms_auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserAuthRepository userAuthRepository, PasswordEncoder passwordEncoder) {
        this.userAuthRepository = userAuthRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws UserInvalidException {
        log.debug("Login user {}", loginRequest.username());
        UserAuth userAuth = userAuthRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new UserInvalidException("Invalid username"));
        if (!passwordEncoder.matches(loginRequest.password(), userAuth.getPassword())) {
            throw new UserInvalidException("Invalid password");
        }
        return new LoginResponse("token");
    }
}
