package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.ViaEntry;
import com.asmsi.admin_system.repository.ViaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViaController {

    @Autowired
    private ViaRepository viaRepository;

    @PostMapping("/save-via")
    public String saveVia(ViaEntry viaEntry) {
        viaRepository.save(viaEntry);
        return "redirect:/via-settings";
    }

}
