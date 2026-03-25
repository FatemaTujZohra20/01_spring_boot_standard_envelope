package com.standard_envelope01.standard_envelope.controller;

import com.standard_envelope01.standard_envelope.dto.ApiResponse;
import com.standard_envelope01.standard_envelope.model.User;
import com.standard_envelope01.standard_envelope.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller without solving the HttpStatusCode

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    private final UserService userService;
    
    public UserController (UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping
    public ApiResponse<User> create(@RequestBody User user) {
        return userService.createUser(user);
    }
    
    @GetMapping
    public ApiResponse<List<User>> getAll() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/{id}")
    public ApiResponse<User> getOneUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
}
