package com.nt.controller;




import com.nt.entity.User;
import com.nt.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
@Autowired
    private  IUserService uService;

   

    // Open registration page
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Register user
    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("user") User user,
            Model model) {
        try {
            uService.registerUser(user);
            return "redirect:/login?registered";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    // Open login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Login user (simple demo, no authentication/session)
    @PostMapping("/login")
    public String loginUser(
            @RequestParam String email,
            @RequestParam String password,
            Model model) {
        try {
            User user = uService.loginUser(email, password);

            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                return "redirect:/admin/dashboard";
            }

            return "redirect:/user/dashboard";

        } catch (RuntimeException e) {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    // User dashboard
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user-dashboard";
    }

    // Admin dashboard
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("users", uService.getAllUsers());
        return "admin-dashboard";
    }

    // Delete user
    @PostMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        uService.deleteUser(id);
        return "redirect:/admin/dashboard";
    }
}
