package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.User;
import com.asmsi.admin_system.service.AuthUtil;
import com.asmsi.admin_system.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthUtil authUtil;

    // Show both approved and unapproved users
    @GetMapping
    public String viewUsers(Model model) {
        model.addAttribute("username", authUtil.getCurrentUser().getUsername());
        model.addAttribute("role", authUtil.getCurrentUser().getRole());

        model.addAttribute("approvedUsers", userService.getApprovedUsers());
        model.addAttribute("unapprovedUsers", userService.getUnapprovedUsers());
        

        return "user-management";
    }
    
    @PostMapping("/approve")
    public String approveUser(@RequestParam("id") Long id) {
        Long adminId = authUtil.getCurrentUser().getId();
        userService.approveUser(id, adminId);
        return "redirect:/users";
    }


    // Reject or delete unapproved user
    @PostMapping("/reject")
    public String rejectUser(@RequestParam("id") Long id) {
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
    @PostMapping("/edit")
    public String updateUserFromModal(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("role") String role
    ) {
        User updatedUser = new User();
        updatedUser.setId(id); // not necessary for the update logic, but okay
        updatedUser.setName(name);
        updatedUser.setEmail(email);
        updatedUser.setUsername(username);
        updatedUser.setRole(role);
    
        userService.updateUser(id, updatedUser);    
        return "redirect:/users";
    }
    


    // Handle edit submission
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User updatedUser) {
        userService.updateUser(id, updatedUser);
        return "redirect:/users";
    }
}
