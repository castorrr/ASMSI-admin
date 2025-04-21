package com.asmsi.admin_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttendanceReportController {

    @GetMapping("/attendancereport")
    public String showAttendanceReportPage() {
        return "attendancereport"; // This matches attendancereport.html in templates/
    }
}
