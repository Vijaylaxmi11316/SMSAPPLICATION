package com.SMSApplication.controller;

import com.SMSApplication.entity.User;
import com.SMSApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // -------- Register Page --------
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email already exists");
            return "register";
        }

        // Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("STUDENT");
        } else {
            user.setRole(user.getRole().toUpperCase());
        }



        userRepository.save(user);
        return "redirect:/login";
    }

    // -------- Login Page --------
    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    
}
