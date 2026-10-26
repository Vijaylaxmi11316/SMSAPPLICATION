package com.SMSApplication.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/default")
    public String defaultAfterLogin(Authentication authentication) {

        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_STUDENT")) {
            return "redirect:/student/home";
        } else if (role.equals("ROLE_STAFF")) {
            return "redirect:/staff/home";
        } else if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin/home";
        }

        return "redirect:/login";
    }
}

