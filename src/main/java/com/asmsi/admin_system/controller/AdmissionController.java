package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.AdmissionData;
import com.asmsi.admin_system.service.AdmissionService;
import com.asmsi.admin_system.service.XmlParserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    // Admission Page
    @GetMapping("/admission")
    public String admissionPage(Model model) {
        model.addAttribute("activePage", "admission");
        return "admission";
    }

    // Upload Database Page
    @GetMapping("/upload-database")
    public String uploadDatabasePage(Model model) {
        model.addAttribute("activePage", "upload-database");
        return "upload-database";
    }

    // Search students based on first name, last name, or both
    @GetMapping("/students/search")
    @ResponseBody
    public List<AdmissionData> searchStudents(@RequestParam(required = false) String firstName,
                                              @RequestParam(required = false) String lastName) {
        return admissionService.searchStudents(firstName, lastName);
    }

    // Handle XML upload and save data to the database
    @PostMapping("/upload-xml")
    public String handleXmlUpload(@RequestParam("schoolYear") String schoolYear,
                                  @RequestParam("xmlFile") MultipartFile file,
                                  Model model) {
        try {
            List<AdmissionData> dataList = XmlParserHelper.parse(file, schoolYear); // Parse XML file
            admissionService.saveAll(dataList); // Save parsed data
            model.addAttribute("successMessage", "XML file successfully uploaded and saved!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to process XML file: " + e.getMessage());
        }

        model.addAttribute("activePage", "upload-database");
        return "upload-database";
    }

   

}
