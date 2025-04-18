package com.asmsi.admin_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendanceController {

    @GetMapping("/attendance")
    public String showAttendancePage() {
        // Return the name of the HTML page to render
        return "attendance"; // This will render attendance.html
    }
    
}
