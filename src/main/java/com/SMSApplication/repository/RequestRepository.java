package com.SMSApplication.repository;

import com.SMSApplication.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByStudentEmail(String email);
}
