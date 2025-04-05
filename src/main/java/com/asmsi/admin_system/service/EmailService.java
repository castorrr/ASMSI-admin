    package com.asmsi.admin_system.service;

    import lombok.RequiredArgsConstructor;
    import org.springframework.mail.SimpleMailMessage;
    import org.springframework.mail.javamail.JavaMailSender;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class EmailService {

        private final JavaMailSender mailSender;

        public void sendApprovalEmail(String toEmail, String fullName) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Account Approved - Sisters of Mary School Portal");
            message.setText("Dear " + fullName + ",\n\nYour account has been approved. You may now log in to the portal.\n\nRegards,\nSMS Team");

            mailSender.send(message);
        }

        public void sendPasswordResetEmail(String toEmail, String token) {
            String resetLink = "http://localhost:8080/reset-password?token=" + token;
    
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Password Reset Request - SMS Portal");
            message.setText("Dear user,\n\nYou have requested to reset your password.\nClick the link below to set a new password (valid for 15 minutes):\n\n" + resetLink + "\n\nIf you did not request this, please ignore this email.\n\nRegards,\nSMS Team");
    
            mailSender.send(message);
        }
    }
