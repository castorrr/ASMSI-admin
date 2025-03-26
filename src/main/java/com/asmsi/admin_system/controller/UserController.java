package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.User;
import com.asmsi.admin_system.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Show both approved and unapproved users
    @GetMapping
public String viewUsers(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User currentUser = (User) auth.getPrincipal();

    model.addAttribute("username", currentUser.getUsername());
    model.addAttribute("role", currentUser.getRole());

    model.addAttribute("approvedUsers", userService.getApprovedUsers());
    model.addAttribute("unapprovedUsers", userService.getUnapprovedUsers());

    return "user-management";
}


    // Approve user
    @GetMapping("/approve/{id}")
    public String approveUser(@PathVariable Long id) {
        // Assuming you store current admin ID via session or auth context
        Long adminId = 1L; // Replace with actual admin ID fetching logic
        userService.approveUser(id, adminId);
        return "redirect:/users";
    }

    // Reject or delete unapproved user
    @GetMapping("/reject/{id}")
    public String rejectUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    // Delete approved user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-edit"; // user-edit.html
    }

    // Handle edit submission
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User updatedUser) {
        userService.updateUser(id, updatedUser);
        return "redirect:/users";
    }
}
