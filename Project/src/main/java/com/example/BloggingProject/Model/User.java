package com.example.BloggingProject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String userType;
        private String fullName;
        private String username;
        private String phone;
        private String password;
        @Transient
        private String confirmPassword;
        private String roleUser;

        public long getId () {
        return id;
    }

        public void setId ( long id){
        this.id = id;
    }

        public String getUserType () {
        return userType;
    }

        public void setUserType (String userType){
        this.userType = userType;
    }

        public String getFullName () {
        return fullName;
    }

        public void setFullName (String fullName){
        this.fullName = fullName;
    }

        public String getUsername () {
        return username;
    }

        public void setUsername (String email){
        this.username = email;
    }

        public String getPhone () {
        return phone;
    }

        public void setPhone (String phone){
        this.phone = phone;
    }

        public String getPassword () {
        return password;
    }

        public void setPassword (String password){
        this.password = password;
    }

        public String getConfirmPassword () {
        return confirmPassword;
    }

        public void setConfirmPassword (String confirmPassword){
        this.confirmPassword = confirmPassword;
    }

        public String getRoleUser () {
        return roleUser;
    }

        public void setRoleUser (String roleUser){
        this.roleUser = roleUser;
    }
}
