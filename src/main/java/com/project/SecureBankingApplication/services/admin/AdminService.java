package com.project.SecureBankingApplication.services.admin;


import com.project.SecureBankingApplication.bo.user.GetUserRequest;
import com.project.SecureBankingApplication.entities.UserEntity;

import java.util.List;

public interface AdminService {

    List<UserEntity> getUsers();
    GetUserRequest getUser(int id);

    void deleteUser(int id);
}
