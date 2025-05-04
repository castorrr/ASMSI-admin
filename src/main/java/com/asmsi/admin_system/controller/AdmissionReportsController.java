package com.asmsi.admin_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdmissionReportsController {

    @GetMapping("/admissionreports")
    public String showAdmissionReports(Model model) {
        // Active sidebar highlight
        model.addAttribute("activePage", "admissionreports");

        // Expand the "Admission" section in the sidebar
        model.addAttribute("admissionExpanded", true);
        model.addAttribute("studentInfoExpanded", false);
        model.addAttribute("attendanceExpanded", false);
        model.addAttribute("eventsExpanded", false);

        return "admissionreports"; // returns templates/admissionreports.html
    }
}
