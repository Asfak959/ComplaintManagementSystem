package com.example.BloggingProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.BloggingProject.Model.Complaint;
import com.example.BloggingProject.Service.ComplaintService;

@Controller
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService; // Service inject kiya

    // 1. Show all posts on Home page
    @GetMapping("/")
    public String showhome(Model model) {
        List<Complaint> posts = complaintService.getAllComplaints();
        model.addAttribute("posts", posts);
        return "index";
    }

    // 2. Show Create Post Form
    @GetMapping("/new")
    public String newPostForm(Model model) {
        model.addAttribute("post", new Complaint());
        return "create_post";
    }

    // 3. Handle Form Submission
    @PostMapping("/save")
    public String savePost(@ModelAttribute Complaint complaint) {
        complaintService.saveComplaint(complaint);
        return "redirect:/";
    }

    // 4. View Single Post
    @GetMapping("/view/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Complaint post = complaintService.getComplaintById(id);
        model.addAttribute("post", post);
        return "view_post";
    }

    // 5. See Complaint (Login page)
    @GetMapping("/Complaint_status")
    public String seeComplaint(Model model) {
        model.addAttribute("post", new Complaint());
        return "compStatusCheck";
    }

    // 6. Admin Panel
    @GetMapping("/Admin")
    public String viewAdmin(Model model) {
        List<Complaint> complaints = complaintService.getAllComplaints();
        model.addAttribute("complaints", complaints);
        return "Admin_panel";
    }

    // 7. Admin - Get all complaints as JSON
    @GetMapping("/Complaints")
    @ResponseBody
    public List<Complaint> listOfComplaints() {
        return complaintService.getAllComplaints();
    }

    // 8. Admin - Update Status
    @PostMapping("/updateStatus/{id}")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        complaintService.updateComplaintStatus(id, status);
        return "redirect:/Admin";
    }

    // 9. Delete Complaint
    @GetMapping("/delete/{id}")
    public String deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return "redirect:/Admin";
    }

    //    show login page
    @GetMapping("/login")
    public String show_login() {
        return "Login";
    }

    //    show signup page
    @GetMapping("/register")
    public String showSignupPage() {
        return "signupForm";
    }
}