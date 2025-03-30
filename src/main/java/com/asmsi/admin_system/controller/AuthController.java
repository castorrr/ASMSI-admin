package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Show signup page
    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup"; // Displays signup.html
    }

    // Handle signup form submission
    @PostMapping("/request-account")
    public String registerUser(@RequestParam String name,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            Model model) {
        String response = userService.requestAccount(name, username, email, password, role);

        if (response.contains("already")) {
            model.addAttribute("error", response); // Show error if username or email is taken
        } else {
            model.addAttribute("success", response);
        }

        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // ✅ Loads `login.html` from `/templates/`
    }

}
