package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.User;
import com.asmsi.admin_system.entity.Student;
import com.asmsi.admin_system.entity.Event;
import com.asmsi.admin_system.repository.StudentRepository;
import com.asmsi.admin_system.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private EventService eventService;

    @GetMapping("/home")
    public String home(Model model) {
        // Get authentication information
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();

        logger.info("✅ Logged in user: {}", loggedInUser.getUsername());
        logger.info("🆔 User ID: {}", loggedInUser.getId());
        logger.info("🔐 Role: {}", loggedInUser.getRole());

        // Add user information to model
        model.addAttribute("username", loggedInUser.getUsername());
        model.addAttribute("userId", loggedInUser.getId());
        model.addAttribute("role", loggedInUser.getRole());
        model.addAttribute("name", loggedInUser.getName());
        
        // Add profile image URL for header avatar
        model.addAttribute("userProfileImage", loggedInUser.getProfileImageUrl());
        
        // Get total students count
        long totalStudents = studentRepository.count();
        model.addAttribute("totalStudents", totalStudents);
        
        // Get enrolled students count (placeholder until you define what "freshman passers" means)
        model.addAttribute("enrolledStudents", 0); // Replace with actual count when ready
        
        // Get 3 most recently added students
        List<Student> recentStudents = studentRepository.findAll(
            PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "id"))
        ).getContent();
        
        model.addAttribute("recentStudents", recentStudents);
        
        // Get upcoming events (events with date after current time)
        List<Event> allEvents = eventService.getAllEvents();
        LocalDateTime now = LocalDateTime.now();
        
        List<Event> upcomingEvents = allEvents.stream()
            .filter(event -> event.getDateTime().isAfter(now))
            .sorted((e1, e2) -> e1.getDateTime().compareTo(e2.getDateTime()))
            .limit(3)
            .collect(Collectors.toList());
            
        model.addAttribute("upcomingEvents", upcomingEvents);

        return "home";
    }
}