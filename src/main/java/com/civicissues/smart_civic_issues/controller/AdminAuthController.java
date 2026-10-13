package com.civicissues.smart_civic_issues.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminAuthController {

    @Value("${app.admin.id}")
    private String adminId;

    @Value("${app.admin.password}")
    private String adminPassword;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {

        String id = credentials.get("adminId");
        String password = credentials.get("password");

        if (id != null && password != null
                && adminId.equals(id)
                && adminPassword.equals(password)) {

            return ResponseEntity.ok(
                    Map.of("success", true, "message", "Admin credentials verified")
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("success", false, "message", "Invalid Admin ID or password")
        );
    }
}