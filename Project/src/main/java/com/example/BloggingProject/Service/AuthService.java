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

    public User findByUserName(String username){
         return ar.findByUsername(username);
    }

    @Transactional
    public User SaveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleUser("USER");
        return ar.save(user);

    }

    //    Show Admin Panel
    public boolean ShowAdminPanel(User user) {
        if (user == null) {
            throw new UsernameNotFoundException("User name not found");
        }
        return "ADMIN".equals(user.getRoleUser());
    }
}
