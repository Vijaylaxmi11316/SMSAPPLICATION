package com.SMSApplication.config;

import com.SMSApplication.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authenticationProvider(authenticationProvider())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/register", "/login", "/css/**", "/js/**").permitAll()

                    // Student pages
                    .requestMatchers("/student/**").hasAnyRole("STUDENT", "ADMIN")

                    // Staff pages
                    .requestMatchers("/staff/**").hasAnyRole("STAFF", "ADMIN")

                    // Admin pages
                    .requestMatchers("/admin/**").hasRole("ADMIN")

                    // everything must be authenticated
                    .anyRequest().authenticated()
            )
            .formLogin(login -> login
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .failureUrl("/login?error=true")
                    .permitAll()
                    .successHandler((request, response, authentication) -> {
                        String role = authentication.getAuthorities().iterator().next().getAuthority();

                        if (role.equals("ROLE_ADMIN")) {
                            response.sendRedirect("/admin/home");
                        } else if (role.equals("ROLE_STAFF")) {
                            response.sendRedirect("/staff/home");
                        } else {
                            response.sendRedirect("/student/home");
                        }
                    })
            )
            .logout(logout -> logout
                    .logoutSuccessUrl("/login")
                    .permitAll()
            );

        return http.build();
    }

   

}
