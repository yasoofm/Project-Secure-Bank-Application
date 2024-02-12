package com.project.SecureBankingApplication.controllers;

import com.project.SecureBankingApplication.bo.user.GetUserRequest;
import com.project.SecureBankingApplication.entities.UserEntity;
import com.project.SecureBankingApplication.services.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserEntity>> getUsers(){
        List<UserEntity> users = adminService.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("user")
    public ResponseEntity<GetUserRequest> getUser(@RequestParam int id){
        GetUserRequest user = adminService.getUser(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("delete-user")
    public ResponseEntity<Void> deleteUser(@RequestParam int id){
        adminService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
