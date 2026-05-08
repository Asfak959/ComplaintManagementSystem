package com.example.BloggingProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.BloggingProject.Model.Complaint;

public interface ComplaintRepo extends JpaRepository<Complaint, Long> {
    
    // 1. Find by Status (Pending, Resolved, etc.)
    List<Complaint> findByStatus(String status);
    
    // 2. Find by userType (jo aap chahte the)
    List<Complaint> findByUserType(String userType);
    
    // 3. Search by title OR description
    List<Complaint> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
        String title, String description);
    
    // 4. Find by userType AND Status
    List<Complaint> findByUserTypeAndStatus(String userType, String status);
    
    // 5. Custom Query - Latest complaints first
    @Query("SELECT c FROM Complaint c ORDER BY c.createdAt DESC")
    List<Complaint> findLatestComplaints();
    
    // 6. Find by user email
    List<Complaint> findByEmail(String email);
}