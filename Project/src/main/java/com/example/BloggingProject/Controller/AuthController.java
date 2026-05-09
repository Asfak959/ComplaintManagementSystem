package com.example.BloggingProject.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.BloggingProject.Model.User;
import com.example.BloggingProject.Service.AuthService;

@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    //    AddUser
    @PostMapping("/userCreated")
    public String AddUser(@ModelAttribute User user) {
        try {
            // Validate that passwords match
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                return "redirect:/login?error=Password required";
            }
            System.out.println(user.getUsername());
            authService.SaveUser(user);
            return "redirect:/login?success=Account created successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login?error=" + e.getMessage();
        }
    }

    @GetMapping("/Adminlogin")
    public String showLoginPage(){
        return "AdminLogin";
    }

    @GetMapping("/dashboard")
    public String ShowAdminlogin(Principal principal) {
        User user = authService.findByUserName(principal.getName());

        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        if ("ADMIN".equals(user.getRoleUser())) {
            return "Admin_panel";
        } else {
            return "index";
        }


    }

}
