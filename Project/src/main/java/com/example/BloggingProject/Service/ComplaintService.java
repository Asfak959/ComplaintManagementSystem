package com.example.BloggingProject.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BloggingProject.Model.Complaint;
import com.example.BloggingProject.Repository.ComplaintRepo;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepo pr;

    // 1. Get all complaints
    public List<Complaint> getAllComplaints() {
        return pr.findAll();
    }

    // 2. Save new complaint
    public Complaint saveComplaint(Complaint complaint) {
        complaint.setCreatedAt(new Date());
        complaint.setUpdatedAt(new Date());
        complaint.setStatus("Pending");
        return pr.save(complaint);
    }

    // 3. Get complaint by ID
    public Complaint getComplaintById(Long id) {
        return pr.findById(id).orElse(null);
    }

    // 4. Update complaint status (Admin use karega)
    public Complaint updateComplaintStatus(Long id, String status) {
        Complaint complaint = pr.findById(id).orElse(null);
        if (complaint != null) {
            complaint.setStatus(status);
            complaint.setUpdatedAt(new Date());
            return pr.save(complaint);
        }
        return null;
    }

    // 5. Delete complaint
    public void deleteComplaint(Long id) {
        pr.deleteById(id);
    }

    // 6. Get pending complaints only
    public List<Complaint> getPendingComplaints() {
        return pr.findByStatus("Pending");
    }

    // 7. Search complaints by keyword
    public List<Complaint> searchComplaints(String keyword) {
        return pr.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }
}