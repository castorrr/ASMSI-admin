package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.User;
import com.asmsi.admin_system.service.AuthUtil;
import com.asmsi.admin_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final AuthUtil authUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    // View logged-in user's profile
    @GetMapping
    public String viewOwnProfile(Model model) {
        User currentUser = authUtil.getCurrentUser();
        model.addAttribute("user", currentUser);
        model.addAttribute("username", currentUser.getUsername());
        // Add user profile image for header fragment
        model.addAttribute("userProfileImage", currentUser.getProfileImageUrl());
        return "profile";
    }

    @PostMapping("/update")
    public String updateProfile(
            @RequestParam("name") String name,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam(value = "currentPassword", required = false) String currentPassword,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            @RequestParam(value = "confirmPassword", required = false) String confirmPassword,
            RedirectAttributes redirectAttributes
    ) {
        User currentUser = authUtil.getCurrentUser();
    
        // Update basic fields
        currentUser.setName(name);
        currentUser.setUsername(username);
        currentUser.setEmail(email);
    
        // Process password update (if applicable)
        if (newPassword != null && !newPassword.isBlank()) {
            if (currentPassword == null || currentPassword.isBlank() || 
                !passwordEncoder.matches(currentPassword, currentUser.getPassword())) {
                redirectAttributes.addFlashAttribute("error", "Current password is incorrect.");
                return "redirect:/profile";
            }
            if (!newPassword.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "New passwords do not match.");
                return "redirect:/profile";
            }
            currentUser.setPassword(passwordEncoder.encode(newPassword));
        }
    
        try {
            userService.updateUser(currentUser.getId(), currentUser);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update profile: " + e.getMessage());
        }
    
        return "redirect:/profile";
    }
    
    // Handle profile image uploads
    @PostMapping("/upload-image")
    public String uploadProfileImage(
            @RequestParam("profileImage") MultipartFile profileImage,
            RedirectAttributes redirectAttributes
    ) {
        User currentUser = authUtil.getCurrentUser();
        
        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                String imageUrl = userService.uploadProfileImage(profileImage);
                currentUser.setProfileImageUrl(imageUrl);
                userService.updateUser(currentUser.getId(), currentUser);
                redirectAttributes.addFlashAttribute("success", "Profile image updated successfully.");
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("error", "Failed to upload image: " + e.getMessage());
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "No image selected.");
        }
        
        return "redirect:/profile";
    }

    // View any user (admin only)
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        User targetUser = userService.getUserById(id);
        User currentUser = authUtil.getCurrentUser();
        model.addAttribute("user", targetUser);
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("userProfileImage", currentUser.getProfileImageUrl());
        model.addAttribute("isAdmin", true);
        return "profile/view";
    }

    // View all approved staff (admin only)
    @GetMapping("/staff")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewStaffProfiles(Model model) {
        User currentUser = authUtil.getCurrentUser();
        model.addAttribute("user", currentUser);
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("userProfileImage", currentUser.getProfileImageUrl());
        model.addAttribute("staffList", userService.getApprovedUsers());
        return "profile/staff-list";
    }
}