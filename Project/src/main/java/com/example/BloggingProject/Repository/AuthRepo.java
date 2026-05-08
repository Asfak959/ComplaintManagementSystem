package com.example.BloggingProject.Repository;

import com.example.BloggingProject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthRepo extends JpaRepository<User, Long> {

    //    find email
    User findByUsername(String username);


}
