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

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder; // ✅ Injected from AppConfig

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) 
                .roles(user.getRole())
                .build();
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

    public void approveUser(Long userId, Long adminId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setApproved(true);
            user.setAdminID(adminId);
            userRepository.save(user);
        }
    }
}
