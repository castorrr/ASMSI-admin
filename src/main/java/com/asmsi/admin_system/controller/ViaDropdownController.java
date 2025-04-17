package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.repository.ViaDropdownRepository;
import com.asmsi.admin_system.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ViaDropdownController {

    @Autowired
    private ViaDropdownRepository viaDropdownRepository;

    @GetMapping("/student-manager")
    public String getStudentPage(Model model) {
        List<String> vias = viaDropdownRepository.findAllUniqueVia();
        System.out.println("✅ VIA values to Thymeleaf: " + vias);
        model.addAttribute("vias", vias);
        model.addAttribute("student", new Student());
        return "studentmanager";
    }

}
