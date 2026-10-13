
package com.civicissues.smart_civic_issues.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.civicissues.smart_civic_issues.entity.User;
import com.civicissues.smart_civic_issues.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepository.save(user);
    }
    @PostMapping("/login")
public String login(@RequestBody User user) {

    User existingUser = userRepository.findByEmail(user.getEmail());

    if (existingUser != null &&
        existingUser.getPassword().equals(user.getPassword())) {

        return "Login successful";
    }

    return "Invalid email or password";
}
}