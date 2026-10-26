package com.SMSApplication.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.SMSApplication.entity.User;
import com.SMSApplication.repository.RequestRepository;
import com.SMSApplication.repository.UserRepository;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    
    // =====================
    // STUDENT HOME
    // =====================
    @GetMapping("/home")
    public String home(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("email", email);
        return "home";
    }

    
    // =====================
    // STUDENT DASHBOARD
    // =====================
    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        String email = principal.getName();
        User student = userRepository.findByEmail(email);

        model.addAttribute("email", email);
        model.addAttribute("student", student);

        return "student-dashboard";
    }


    // =====================
    // VIEW ALL REQUESTS
    // =====================
    @GetMapping("/requests")
    public String viewRequests(Principal principal, Model model) {
        String email = principal.getName();

        model.addAttribute("email", email);
        model.addAttribute("student", userRepository.findByEmail(email));
        model.addAttribute("requests", requestRepository.findByStudentEmail(email));

        return "student-request";
    }

    @PostMapping("/requests/add")
    public String addRequest(com.SMSApplication.entity.Request req, Principal principal) {
        req.setStudentEmail(principal.getName());
        req.setStatus("Waiting");
        requestRepository.save(req);
        return "redirect:/student/requests";
    }

    @GetMapping("/request/delete/{id}")
    public String deleteRequest(@PathVariable Long id) {
        requestRepository.deleteById(id);
        return "redirect:/student/requests";
    }

    @PostMapping("/reply")
    public String studentReply(long id, String studentComment) {
        var r = requestRepository.findById(id).orElse(null);
        r.setStudentComment(studentComment);
        r.setStatus("Closed");
        requestRepository.save(r);

        return "redirect:/student/requests";
    }


    // =====================
    // STUDENT PROFILE (VIEW)
    // =====================
 // VIEW profile for logged-in user
    @GetMapping("/profile")
    public String viewProfile(Principal principal, Model model) {
        String email = principal.getName();
        User student = userRepository.findByEmail(email);
        if (student == null) {
            throw new IllegalArgumentException("User not found: " + email);
        }
        model.addAttribute("student", student);
        return "student-profile";
    }

    // UPDATE profile (POST)
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("student") User updated, Principal principal) {
        String email = principal.getName();
        User u = userRepository.findByEmail(email);
        if (u == null) {
            throw new IllegalArgumentException("User not found: " + email);
        }

        // only update allowed/editable fields (do not overwrite password/role)
        u.setFirstName(updated.getFirstName());
        u.setLastName(updated.getLastName());
        u.setRollNumber(updated.getRollNumber());
        u.setPhone(updated.getPhone());
        u.setDepartment(updated.getDepartment());
        u.setYearSemester(updated.getYearSemester());
        u.setDob(updated.getDob());
        u.setGender(updated.getGender());
        u.setFatherName(updated.getFatherName());
        u.setAddress(updated.getAddress());

        userRepository.save(u);

        // redirect back to profile page
        return "redirect:/student/profile";
    

    }
}
