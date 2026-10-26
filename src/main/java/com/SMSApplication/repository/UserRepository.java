package com.SMSApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SMSApplication.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByRole(String role);
}
