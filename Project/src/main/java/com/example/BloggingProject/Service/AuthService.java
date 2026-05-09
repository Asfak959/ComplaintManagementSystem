package com.example.BloggingProject.Service;

import com.example.BloggingProject.Model.User;

import com.example.BloggingProject.Repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthRepo ar;

    @Transactional
    public User SaveUser(User user) {
        // Validate required fields
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Email/Username is required");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (user.getFullName() == null || user.getFullName().isEmpty()) {
            throw new IllegalArgumentException("Full Name is required");
        }
        
        // Check if user already exists
        User existing = ar.findByUsername(user.getUsername());
        if (existing != null) {
            throw new IllegalArgumentException("User already exists with this email");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleUser("USER");
        user.setConfirmPassword(null);  // Don't store confirm password
        return ar.save(user);
    }

    public User findByUserName(String username) {
        return ar.findByUsername(username);
    }

    //    Show Admin Panel
    public boolean ShowAdminPanel(User user) {
        if (user == null) {
            throw new UsernameNotFoundException("User name not found");
        }
        return "ADMIN".equals(user.getRoleUser());
    }
}
