package com.SMSApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.SMSApplication.entity.StudentRequest;

public interface StudentRequestRepository extends JpaRepository<StudentRequest,Integer>{
    List<StudentRequest> findByStudentEmail(String email);
}
