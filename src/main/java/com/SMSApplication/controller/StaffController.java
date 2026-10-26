package com.SMSApplication.controller;

import com.SMSApplication.entity.Request;
import com.SMSApplication.entity.User;
import com.SMSApplication.repository.RequestRepository;
import com.SMSApplication.repository.UserRepository;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private UserRepository userRepository;

    // ---------------------- Staff Home ----------------------
    @GetMapping("/home")
    public String staffHome(Principal principal, Model model) {
        model.addAttribute("email", principal.getName());
        return "staff-home";   // FIXED (this file exists)
    }

    // ---------------------- Staff Dashboard (direct URL) ----------------------
    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        model.addAttribute("email", principal.getName());
        return "staff-dashboard";  // staff-dashboard.html
    }
    
    

    // ---------------------- List Students ----------------------
    @GetMapping("/students")
    public String manageStudents(Model model) {
        model.addAttribute("students", userRepository.findByRole("STUDENT"));
        return "students";
    }

    // ---------------------- Add Student Form ----------------------
    @GetMapping("/add-student")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new User());
        return "staff-add-student";
    }

    // ---------------------- Add Student POST ----------------------
    @PostMapping("/add-student")
    public String addStudentSubmit(@ModelAttribute("student") User student) {
        student.setRole("STUDENT");
        userRepository.save(student);
        return "redirect:/staff/students";
    }

    // ---------------------- Edit Student Form ----------------------
    @GetMapping("/edit-student/{id}")
    public String editStudentForm(@PathVariable("id") Long id, Model model) {
        User student = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "staff-edit-student";
    }

    // ---------------------- Edit Student POST ----------------------
    @PostMapping("/edit-student/{id}")
    public String editStudentSubmit(@PathVariable("id") Long id,
                                    @ModelAttribute("student") User studentDetails) {
        User student = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        userRepository.save(student);
        return "redirect:/staff/students";
    }

    // ---------------------- Delete Student ----------------------
    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        User student = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        userRepository.delete(student);
        return "redirect:/staff/students";
    }
    

    @Autowired
    private RequestRepository requestRepository;

    @GetMapping("/requests")
    public String viewStaffRequests(Model model) {
        model.addAttribute("requests", requestRepository.findAll());
        return "staff-requests"; 
    }
    

 // APPROVE
 @GetMapping("/approve/{id}")
 public String approveRequest(@PathVariable Long id) {
     Request r = requestRepository.findById(id)
             .orElseThrow(() -> new IllegalArgumentException("Invalid request id: " + id));

     r.setStatus("Approved");
     requestRepository.save(r);

     return "redirect:/staff/requests";
 }

 // REJECT
 @GetMapping("/reject/{id}")
 public String rejectRequest(@PathVariable Long id) {
     Request r = requestRepository.findById(id)
             .orElseThrow(() -> new IllegalArgumentException("Invalid request id: " + id));

     r.setStatus("Rejected");
     requestRepository.save(r);

     return "redirect:/staff/requests";
 }

 // COMMENT
 @PostMapping("/comment")
 public String addComment(@RequestParam Long id,
                          @RequestParam String staffComment) {

     Request r = requestRepository.findById(id)
             .orElseThrow(() -> new IllegalArgumentException("Invalid request id: " + id));

     r.setStaffComment(staffComment);
     requestRepository.save(r);

     return "redirect:/staff/requests";
 }
 @GetMapping("/admin/staff/edit/{id}")
 public String editStaff(@PathVariable Long id, Model model) {
     User staff = userRepository.findById(id).orElse(null);
     if (staff == null || !staff.getRole().equals("STAFF")) {
         return "redirect:/admin/staffs?error=StaffNotFound";
     }

     model.addAttribute("staff", staff);
     return "admin-edit-staff"; // Thymeleaf page name
 }

    
}


