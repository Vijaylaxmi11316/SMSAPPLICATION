package com.SMSApplication.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Replace these with your users' plain passwords
        String[] passwords = {
            "sai123", "laxmi123", "vijaya123", "visani123", "student123", "student1234", "vijaylaxmi123"
        };

        for (String pw : passwords) {
            System.out.println(pw + " -> " + encoder.encode(pw));
        }
    }
}
