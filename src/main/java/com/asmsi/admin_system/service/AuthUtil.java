package com.asmsi.admin_system.service;

import com.asmsi.admin_system.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public String getCurrentUserRole() {
        return getCurrentUser().getRole();
    }

    public String getCurrentUsername() {
        return getCurrentUser().getUsername();
    }       
}
