package com.project.SecureBankingApplication.controllers;

import com.project.SecureBankingApplication.bo.user.CreateUserRequest;
import com.project.SecureBankingApplication.bo.user.GetUserRequest;
import com.project.SecureBankingApplication.bo.user.UpdateUserRequest;
import com.project.SecureBankingApplication.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest){
            userService.createUser(createUserRequest);
            return ResponseEntity.ok().build();
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest updateUserRequest, @RequestParam int id){
        userService.updateUser(updateUserRequest, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/profile")
    public ResponseEntity<GetUserRequest> getUser(@RequestParam int id){
        GetUserRequest getUserRequest = userService.getUser(id);
        return ResponseEntity.ok().body(getUserRequest);
    }
}
