package com.standard_envelope01.standard_envelope.controller;

import com.standard_envelope01.standard_envelope.dto.ApiResponse;
import com.standard_envelope01.standard_envelope.model.User;
import com.standard_envelope01.standard_envelope.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller with solving the HttpStatusCode

/**
 * REST Controller for User Management.
 * This layer handles HTTP requests and wraps our generic ApiResponse
 * into a ResponseEntity to provide professional HTTP Status Codes.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    private final UserService userService;
    
    // Constructor Injection
    public UserController (UserService userService) {
        this.userService = userService;
    }
    
    /**
     * POST: Create a new user resource.
     * returns: 201 Created with the saved user data.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<User>> create (@RequestBody User user) {
        // 1. Call the service to get our custom envelope
        ApiResponse<User> response = userService.createUser(user);
        
        // 2. Wrap it in a ResponseEntity with the 201 status
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * GET: Retrieve all users from the system.
     * returns: 200 OK with the list of users (or an empty list).
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAll () {
        // 1. Fetch the generic response from the service
        ApiResponse<List<User>> response = userService.getAllUsers();
        
        // 2. Wrap the response in a ResponseEntity with 200 OK status
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    /**
     * GET: Retrieve a specific user by their ID.
     * returns: 200 OK if found, or 404 NOT FOUND if the user does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getOneUser (@PathVariable Long id) {
        // 1. Call the service to find the user
        ApiResponse<User> response = userService.getUserById(id);
        
        // 2. Logic Check: If the service marked success as false, return 404
        if (!response.success()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        
        // 3. Otherwise, return the user with 200 OK
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
