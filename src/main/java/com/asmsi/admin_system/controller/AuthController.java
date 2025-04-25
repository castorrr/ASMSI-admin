package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.asmsi.admin_system.service.EmailService;
import com.asmsi.admin_system.service.TokenUtil;
import com.asmsi.admin_system.entity.PasswordResetToken;
import com.asmsi.admin_system.entity.User;
import com.asmsi.admin_system.repository.PasswordResetTokenRepository;
import com.asmsi.admin_system.repository.UserRepository;

@Controller
@RequiredArgsConstructor
@Slf4j // <--- This adds the logger instance
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordTokenRepository;
    private final EmailService emailService;

    @GetMapping("/")
    public String showLandingPage() {
        return "landing-page"; // Assuming you have an index.html template
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        log.info("Navigated to signup page.");
        return "signup";
    }

    @PostMapping("/request-account")
    public String registerUser(
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role,
            Model model) {

        log.info("Signup requested by: {}", email);

        String response = userService.requestAccount(name, username, email, password, role);

        if (response.contains("already")) {
            log.warn("Signup failed: {}", response);
            model.addAttribute("error", response);
        } else {
            log.info("Signup success for: {}", email);
            model.addAttribute("success", response);
        }

        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        log.info("Navigated to login page.");
        return "login";
    }

    @GetMapping("/forget-password")
    public String showForgotPasswordPage() {
        log.info("Navigated to forget password page.");
        return "forget-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        log.info("Processing forgot password for email: {}", email);

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            String token = TokenUtil.generateResetToken();
            log.debug("Generated token: {}", token);

            PasswordResetToken resetToken = PasswordResetToken.builder()
                    .token(token)
                    .user(userOptional.get())
                    .expiryDate(LocalDateTime.now().plusMinutes(15))
                    .build();

            passwordTokenRepository.save(resetToken);
            log.info("Saved password reset token for user: {}", email);

            emailService.sendPasswordResetEmail(email, token);
            log.info("Sent password reset email to: {}", email);

            model.addAttribute("success", "A reset link has been sent to your email.");
        } else {
            log.warn("No account found for email: {}", email);
            model.addAttribute("error", "No account found with that email.");
        }

        return "forget-password";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordPage(@RequestParam("token") String token, Model model) {
        log.info("Accessed reset-password page with token: {}", token);

        Optional<PasswordResetToken> tokenOptional = passwordTokenRepository.findByToken(token);

        if (tokenOptional.isEmpty() || tokenOptional.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            log.warn("Invalid or expired token accessed: {}", token);
            model.addAttribute("error", "Token is invalid or has expired.");
            return "reset-password";
        }

        model.addAttribute("token", token);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String handlePasswordReset(
            @RequestParam("token") String token,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {

        log.info("Handling password reset for token: {}", token);

        if (!password.equals(confirmPassword)) {
            log.warn("Password mismatch for token: {}", token);
            model.addAttribute("error", "Passwords do not match.");
            model.addAttribute("token", token);
            return "reset-password";
        }

        Optional<PasswordResetToken> tokenOptional = passwordTokenRepository.findByToken(token);

        if (tokenOptional.isEmpty() || tokenOptional.get().getExpiryDate().isBefore(LocalDateTime.now())) {
            log.warn("Invalid or expired token during reset: {}", token);
            model.addAttribute("error", "Token is invalid or has expired.");
            return "reset-password";
        }

        User user = tokenOptional.get().getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userRepository.save(user);
        log.info("Password successfully reset for user: {}", user.getEmail());

        passwordTokenRepository.delete(tokenOptional.get());
        log.debug("Deleted used reset token: {}", token);

        model.addAttribute("success", "Password has been reset successfully.");
        return "login";
    }
}
