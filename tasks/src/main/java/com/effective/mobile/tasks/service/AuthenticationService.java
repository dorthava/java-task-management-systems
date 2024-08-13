package com.effective.mobile.tasks.service;

import com.effective.mobile.tasks.dto.JwtAuthenticationResponse;
import com.effective.mobile.tasks.dto.SignInRequest;
import com.effective.mobile.tasks.dto.SignUpRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
}
