
package com.civicissues.smart_civic_issues.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
            "project", "Smart Civic Issues System",
            "status", "Backend is running successfully!",
            "api", "/api/issues"
        );
    }
}
