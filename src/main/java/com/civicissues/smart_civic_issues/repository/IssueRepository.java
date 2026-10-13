package com.civicissues.smart_civic_issues.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.civicissues.smart_civic_issues.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {

}