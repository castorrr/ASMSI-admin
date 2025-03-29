package com.asmsi.admin_system.service;

import com.asmsi.admin_system.entity.User;
import com.asmsi.admin_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; // ✅ Injected from AppConfig
    private final EmailService emailService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        
        System.out.println("🔐 Logging in: " + user.getUsername());
        System.out.println("📛 Role from DB: " + user.getRole());
        System.out.println("✅ Authority returned: ROLE_" + user.getRole().toUpperCase());

        return user; 
    }

    public String requestAccount(String name, String username, String email, String password, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            return "Username already taken!";
        }
        if (userRepository.findByEmail(email).isPresent()) {
            return "Email is already in use!";
        }

        User user = User.builder()
                .name(name)
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password)) // ✅ Hash the password properly
                .role(role)
                .isApproved(false)
                .build();

        userRepository.save(user);
        return "Account request submitted successfully!";
    }

    public List<User> getUnapprovedUsers() {
        return userRepository.findByIsApprovedFalse();
    }

    public List<User> getApprovedUsers() {
        return userRepository.findByIsApprovedTrue();
    }

    public void approveUser(Long userId, Long adminId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setApproved(true);
            user.setAdminId(adminId);
            user.setDateApproved(LocalDate.now()); // Set the date of approval
            emailService.sendApprovalEmail(user.getEmail(), user.getName()); // Send approval email
            userRepository.save(user);
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    public void updateUser(Long id, User updated) {
        User existing = userRepository.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setUsername(updated.getUsername());
        existing.setRole(updated.getRole());
        userRepository.save(existing);
    }
    
}
