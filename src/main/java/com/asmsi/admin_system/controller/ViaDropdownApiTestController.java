package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.repository.ViaDropdownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ViaDropdownApiTestController {

    @Autowired
    private ViaDropdownRepository repo;

    @GetMapping("/api/test-vias")
    public List<String> getTestVias() {
        return repo.findAllUniqueVia();
    }
}
