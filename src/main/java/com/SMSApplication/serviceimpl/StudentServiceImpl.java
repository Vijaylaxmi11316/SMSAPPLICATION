//package com.SMSApplication.serviceimpl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.SMSApplication.entity.Student;
//import com.SMSApplication.repository.Studentrepository;
//import com.SMSApplication.service.StudentService;
//
//@Service
//public class StudentServiceImpl implements StudentService {
//
//    @Autowired
//    private Studentrepository repo;
//
//    @Override
//    public List<Student> getAllStudents() {
//        return repo.findAll();
//    }
//
//    @Override
//    public Student saveStudent(Student student) {
//        return repo.save(student);
//    }
//
//    @Override
//    public Student getById(int id) {
//        return repo.findById(id).orElse(null);
//    }
//
//    @Override
//    public void deleteById(int id) {
//        repo.deleteById(id);
//    }
//
//    @Override
//    public Student getByEmail(String email) {
//        return repo.findByEmail(email);
//    }
//}
