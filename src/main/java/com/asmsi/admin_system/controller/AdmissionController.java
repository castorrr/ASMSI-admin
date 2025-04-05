package com.asmsi.admin_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.asmsi.admin_system.service.AuthUtil;

@Controller
public class AdmissionController {

    private final AuthUtil authUtil = new AuthUtil();

    @GetMapping("/admission")
    public String admissionPage(Model model) {
       
        model.addAttribute("username", authUtil.getCurrentUser().getUsername());
        model.addAttribute("role", authUtil.getCurrentUser().getRole());
        return "admission"; // Renders src/main/resources/templates/admission.html
    }
}
