package com.civicissues.smart_civic_issues.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civicissues.smart_civic_issues.entity.User;
import com.civicissues.smart_civic_issues.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}