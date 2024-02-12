package com.project.SecureBankingApplication.services.user;

import com.project.SecureBankingApplication.bo.user.CreateUserRequest;
import com.project.SecureBankingApplication.bo.user.GetUserRequest;
import com.project.SecureBankingApplication.bo.user.UpdateUserRequest;

public interface UserService {
    void createUser(CreateUserRequest createUserRequest);

    void updateUser(UpdateUserRequest updateUserRequest, int id);

    GetUserRequest getUser(int id);

}
