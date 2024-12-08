package com.example.Practice_SpringBoot.controller;


import com.example.Practice_SpringBoot.Exception.UserNotFoundException;
import com.example.Practice_SpringBoot.entity.UserDetails;
import com.example.Practice_SpringBoot.service.UserDetailsService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserDetailsController {
    @Autowired
    public UserDetailsService userDetailsService;

    @PostMapping("/api/user/register")
    public ResponseEntity<String> registeruser(@RequestParam String userName,
                                               @RequestParam String passwordHash,
                                               @RequestParam String role,
                                               @RequestParam String email,
                                               @RequestParam String phoneNumber,
                                               @RequestParam boolean isActive) {
        userDetailsService.saveUserDeatils(new UserDetails(userName, passwordHash, role, email, phoneNumber, isActive));
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<?> loginUser(
            @RequestParam("userName") String userName,
            @RequestParam("passwordHash") String passwordHash,
            @RequestParam("role") String role
    ) {
        // Step 1: Authenticate user credentials
        boolean isAuthenticated = userDetailsService.authenticateUser(userName, passwordHash, role);

        if (isAuthenticated) {
            // Step 2: Fetch user details from the database
            UserDetails user = userDetailsService.getUserByUserName(userName); // Ensure this method exists

            if (user != null) {
                // Step 3: Prepare the response map
                Map<String, Object> response = new HashMap<>();
                response.put("userId", user.getUserId());
                response.put("userName", user.getUserName());
                response.put("role", user.getRole());
                response.put("message", "Login successful!");

                // Step 4: Return the response
                return ResponseEntity.ok(response);
            } else {
                // If user details are not found
                return ResponseEntity.status(404).body("User details not found.");
            }
        }
        // If authentication fails
        return ResponseEntity.status(401).body("Invalid credentials or role.");
    }
    @GetMapping("/api/user/{userId}")
    public ResponseEntity<UserDetails> getByUserId(@PathVariable Long userId) {
        UserDetails userDetails = userDetailsService.getByUserId(userId);
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userDetails);
    }


}
