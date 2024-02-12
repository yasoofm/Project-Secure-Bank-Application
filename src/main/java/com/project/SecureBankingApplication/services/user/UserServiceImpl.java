package com.project.SecureBankingApplication.services.user;

import com.project.SecureBankingApplication.bo.user.CreateUserRequest;
import com.project.SecureBankingApplication.bo.user.GetUserRequest;
import com.project.SecureBankingApplication.bo.user.UpdateUserRequest;
import com.project.SecureBankingApplication.entities.AccountEntity;
import com.project.SecureBankingApplication.entities.UserEntity;
import com.project.SecureBankingApplication.repositories.AccountRepository;
import com.project.SecureBankingApplication.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        UserEntity user = new UserEntity();
        user.setAddress(createUserRequest.getAddress());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setPhonenumber(createUserRequest.getPhonenumber());
        user.setUsername(createUserRequest.getUsername());
        userRepository.save(user);

        AccountEntity account = new AccountEntity();
        account.setUser(user);
        accountRepository.save(account);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest, int id) {
        UserEntity user = userRepository.getById(id);
        user.setUsername(updateUserRequest.getUsername());
        user.setPhonenumber(updateUserRequest.getPhonenumber());
        user.setEmail(updateUserRequest.getEmail());
        user.setAddress(updateUserRequest.getAddress());
        user.setPassword(updateUserRequest.getPassword());
        userRepository.save(user);
    }

    @Override
    public GetUserRequest getUser(int id) {
        UserEntity user = userRepository.getById(id);
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setAddress(user.getAddress());
        getUserRequest.setEmail(user.getEmail());
        getUserRequest.setPhonenumber(user.getPhonenumber());
        getUserRequest.setPassword(user.getPassword());
        getUserRequest.setUsername(user.getUsername());
        getUserRequest.setId(user.getId());
        return getUserRequest;
    }
}
