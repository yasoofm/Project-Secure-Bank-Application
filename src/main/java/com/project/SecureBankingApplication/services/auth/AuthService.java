package com.project.SecureBankingApplication.services.auth;

import com.project.SecureBankingApplication.bo.auth.AuthenticationResponse;
import com.project.SecureBankingApplication.bo.auth.CreateLoginRequest;
import com.project.SecureBankingApplication.bo.user.CreateUserRequest;

public interface AuthService {
    void signup(CreateUserRequest createUserRequest);
    AuthenticationResponse login(CreateLoginRequest createLoginRequest);
}
