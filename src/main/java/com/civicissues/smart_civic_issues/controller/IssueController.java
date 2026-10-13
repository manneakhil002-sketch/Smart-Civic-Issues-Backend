package com.civicissues.smart_civic_issues.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.civicissues.smart_civic_issues.entity.Issue;
import com.civicissues.smart_civic_issues.repository.IssueRepository;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "http://localhost:5173")
public class IssueController {

    private final IssueRepository issueRepository;

    public IssueController(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    // Create complaint
    @PostMapping
    public Issue createIssue(@RequestBody Issue issue) {
        if (issue.getStatus() == null || issue.getStatus().isBlank()) {
            issue.setStatus("Pending");
        }
        return issueRepository.save(issue);
    }

    // Get all complaints
    @GetMapping
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    // Update complaint status
    @PutMapping("/{id}/status")
    public Issue updateStatus(
            @PathVariable Long id,
            @RequestBody StatusRequest request) {

        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Complaint not found"));

        if (request.getStatus() == null ||
                !(request.getStatus().equals("Pending")
                || request.getStatus().equals("In Progress")
                || request.getStatus().equals("Resolved"))) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid complaint status");
        }

        issue.setStatus(request.getStatus());
        return issueRepository.save(issue);
    }

    // Delete complaint
    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable Long id) {
        if (!issueRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Complaint not found");
        }

        issueRepository.deleteById(id);
    }

    public static class StatusRequest {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}