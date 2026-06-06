package com.soaint.ms_auth.service;

import com.soaint.ms_auth.dto.LoginRequest;
import com.soaint.ms_auth.dto.LoginResponse;
import com.soaint.ms_auth.exception.UserInvalidException;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest) throws UserInvalidException;
}
