package com.project.SecureBankingApplication.services.auth;

import com.project.SecureBankingApplication.bo.auth.AuthenticationResponse;
import com.project.SecureBankingApplication.bo.auth.CreateLoginRequest;
import com.project.SecureBankingApplication.bo.auth.CustomUserDetails;
import com.project.SecureBankingApplication.bo.user.CreateUserRequest;
import com.project.SecureBankingApplication.configs.JWTUtil;
import com.project.SecureBankingApplication.entities.RoleEntity;
import com.project.SecureBankingApplication.entities.UserEntity;
import com.project.SecureBankingApplication.repositories.RoleRepository;
import com.project.SecureBankingApplication.repositories.UserRepository;
import com.project.SecureBankingApplication.utils.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JWTUtil jwtUtil, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void signup(CreateUserRequest createUserRequest) {
        RoleEntity roleEntity= roleRepository.findRoleEntityByTitle(Role.USER.name())
                .orElseThrow();
        UserEntity user = new UserEntity();
        user.setUsername(createUserRequest.getUsername());
        user.setAddress(createUserRequest.getAddress());
        user.setEmail(createUserRequest.getEmail());
        user.setPhonenumber(createUserRequest.getPhonenumber());
        user.setRole(roleEntity);
        user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
        userRepository.save(user);
    }

    @Override
    public AuthenticationResponse login(CreateLoginRequest createLoginRequest) {
        if (createLoginRequest.getPassword() == null || createLoginRequest.getUsername() == null){
            throw new NullPointerException();
        }
        String username = createLoginRequest.getUsername().toLowerCase();
        String password = createLoginRequest.getPassword();

        authentication(username, password);

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String accessToken = jwtUtil.generateToken(userDetails);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setId(userDetails.getId());
        response.setUsername(userDetails.getUsername());
        response.setRole(userDetails.getRole());
        response.setToken("Bearer " + accessToken);
        return response;
    }

    private void authentication(String username, String password){
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
