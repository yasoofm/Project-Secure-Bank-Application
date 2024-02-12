package com.project.SecureBankingApplication.controllers;

import com.project.SecureBankingApplication.bo.auth.AuthenticationResponse;
import com.project.SecureBankingApplication.bo.auth.CreateLoginRequest;
import com.project.SecureBankingApplication.bo.user.CreateUserRequest;
import com.project.SecureBankingApplication.services.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest createUserRequest){
        authService.signup(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody CreateLoginRequest createLoginRequest){
        AuthenticationResponse response = authService.login(createLoginRequest);
        HttpStatus status = HttpStatus.OK;
        if (response == null){
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, status);
    }
}
