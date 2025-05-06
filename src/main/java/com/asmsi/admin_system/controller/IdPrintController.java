package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.AdmissionData;
import com.asmsi.admin_system.repository.AdmissionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IdPrintController {

    @Autowired
    private AdmissionDataRepository admissionDataRepository;

    @GetMapping("/print-id/{idNum}")
    public String printId(@PathVariable String idNum, Model model) {
        AdmissionData student = admissionDataRepository.findByIdNum(idNum)
            .stream()
            .findFirst()
            .orElse(null);

        if (student == null) {
            return "error/404"; // or create a fallback page
        }

        model.addAttribute("student", student);
        return "id-card"; // This is the id-card.html template
    }
}
