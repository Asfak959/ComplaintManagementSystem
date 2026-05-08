package com.example.BloggingProject.Controller;

import com.example.BloggingProject.Model.User;
import com.example.BloggingProject.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    //    AddUser
    @PostMapping("/userCreated")
    public String AddUser(@ModelAttribute User user) {
        authService.SaveUser(user);
        return "redirect:/";
    }

     @GetMapping("/Adminlogin")
    public String ShowAdminlogin(Principal principal) {
        User user = authService.findByUserName(principal.getName());

        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        if ("ADMIN".equals(user.getRoleUser())) {
            return "Admin_panel";
        }else{
            return "AccessDenied";
        }

    }

}
