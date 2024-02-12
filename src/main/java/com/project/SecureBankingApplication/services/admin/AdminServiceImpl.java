package com.project.SecureBankingApplication.services.admin;

import com.project.SecureBankingApplication.bo.user.GetUserRequest;
import com.project.SecureBankingApplication.entities.UserEntity;
import com.project.SecureBankingApplication.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users;
    }

    @Override
    public GetUserRequest getUser(int id) {
        UserEntity user = userRepository.getById(id);
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setAddress(user.getAddress());
        getUserRequest.setEmail(user.getEmail());
        getUserRequest.setPassword(user.getPassword());
        getUserRequest.setPhonenumber(user.getPhonenumber());
        getUserRequest.setUsername(user.getUsername());
        getUserRequest.setId(user.getId());
        return getUserRequest;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
