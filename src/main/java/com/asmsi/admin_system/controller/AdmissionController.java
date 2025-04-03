package com.asmsi.admin_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class AdmissionController {

    @GetMapping("/admission")
    public String admissionPage(Model model) {
        return "admission"; // Renders src/main/resources/templates/admission.html
    }
}
