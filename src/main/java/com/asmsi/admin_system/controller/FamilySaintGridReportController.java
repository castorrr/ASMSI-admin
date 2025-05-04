package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.dto.FamilySaintGridEntry;
import com.asmsi.admin_system.entity.FinalStudent;
import com.asmsi.admin_system.repository.FinalStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/admission-grid") // ✅ changed from "/admissionreports"
public class FamilySaintGridReportController {

    @Autowired
    private FinalStudentRepository finalStudentRepository;

    @GetMapping
    public String showFamilySaintGrid(Model model) {
        List<String> saints = finalStudentRepository.findDistinctFamilySaints();
        Map<String, List<FamilySaintGridEntry>> saintGridMap = new LinkedHashMap<>();

        for (String saint : saints) {
            List<FinalStudent> students = finalStudentRepository.findByFamilySaint(saint);
            List<FamilySaintGridEntry> rows = new ArrayList<>();

            for (int i = 1; i <= 50; i++) {
                int code = i;
                FinalStudent match = students.stream()
                        .filter(s -> s.getCodeNumber() == code)
                        .findFirst()
                        .orElse(null);

                FamilySaintGridEntry entry = new FamilySaintGridEntry();
                entry.setCodeNumber(i);
                entry.setStudentName(match != null ? match.getLastName() + ", " + match.getFirstName() : "");
                entry.setProvince(match != null ? match.getProvince() : "");
                rows.add(entry);
            }

            saintGridMap.put(saint, rows);
        }

        model.addAttribute("saintGridMap", saintGridMap);
        return "admissiongrid"; // your Thymeleaf template name (admissiongrid.html)
    }
}