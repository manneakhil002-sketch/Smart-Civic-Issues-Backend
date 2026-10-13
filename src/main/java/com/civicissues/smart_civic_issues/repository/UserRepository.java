package com.civicissues.smart_civic_issues.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.civicissues.smart_civic_issues.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}