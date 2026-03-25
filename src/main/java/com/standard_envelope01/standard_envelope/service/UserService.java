package com.standard_envelope01.standard_envelope.service;

import com.standard_envelope01.standard_envelope.dto.ApiResponse;
import com.standard_envelope01.standard_envelope.model.User;
import com.standard_envelope01.standard_envelope.repo.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public ApiResponse<User> createUser (User user) {
        
        User savedUser = userRepository.save(user);
        
        return ApiResponse.success("User created successfully", savedUser);
    }
    
//    Note: the following can cause a nullPointerException
//    public ApiResponse<List<User>> getAllUsers () {
//        List<User> users = userRepository.findAll();
//        return ApiResponse.success("Users fetched successfully", users);
//    }
    
    public ApiResponse<List<User>> getAllUsers () {
        return Optional.of(userRepository.findAll())
                .filter(list -> !list.isEmpty())    // Only move forward if the list has data
                .map(users -> ApiResponse.success("Users retrieved", users))
                .orElseGet(() -> ApiResponse.success("Zero users found", List.of()));
                // List.of() returns a safe, empty, immutable list
        
    }
    
    public ApiResponse<User> getUserById (Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        
        return userOptional
                .map(user -> ApiResponse.success("User found", user))
                .orElseGet(() -> ApiResponse.<User>builder()
                        .message("User not found with ID: " + id)
                        .success(false)
                        .build());
        
    }
    
}
