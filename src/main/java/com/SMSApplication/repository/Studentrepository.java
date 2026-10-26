package com.SMSApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SMSApplication.entity.Student;

public interface Studentrepository extends JpaRepository<Student,Integer> {
    Student findByEmail(String email);
}
