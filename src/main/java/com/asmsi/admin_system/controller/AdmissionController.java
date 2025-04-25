package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.AdmissionData;
import com.asmsi.admin_system.entity.FinalStudent;
import com.asmsi.admin_system.repository.FinalStudentRepository;
import com.asmsi.admin_system.service.AdmissionService;
import com.asmsi.admin_system.service.XmlParserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    //HANDLE SAVING FORM TO DATABASE
    @Autowired
    private FinalStudentRepository finalStudentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/submit")
    public String submitForm(@RequestParam String lastName, 
                             @RequestParam String firstName, 
                             @RequestParam String middleName,
                             @RequestParam String suffix, 
                             @RequestParam String barangay,
                             @RequestParam String municipality, 
                             @RequestParam String province,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate, 
                             @RequestParam String birthPlace,
                             @RequestParam int age,
                             @RequestParam String examPlace,
                             @RequestParam String idNumber,
                             @RequestParam String lrn,
                             @RequestParam String religion, 
                             @RequestParam String gender, 
                             @RequestParam String citizenship,
                             @RequestParam String elementarySchool, 
                             @RequestParam String elemAddress,
                             @RequestParam String fatherName,
                             @RequestParam String fatherOccupation,
                             @RequestParam String fatherContact,
                             @RequestParam String motherName,
                             @RequestParam String motherOccupation,
                             @RequestParam String motherContact,
                             @RequestParam String guardianName,
                             @RequestParam String guardianRelationship,
                             @RequestParam String guardianOccupation,
                             @RequestParam String guardianContact,
                             @RequestParam String psastatus,
                             @RequestParam String baptismalstatus,
                             @RequestParam String confirmationstatus,
                             @RequestParam String goodmoralstatus,
                             @RequestParam String form138status,
                             @RequestParam String indigencystatus,
                             @RequestParam String philhealthstatus,
                             @RequestParam String kasunduansatus,
                             @RequestParam String photoStatus,
                             @RequestParam String medicalrecordstatus,
                             @RequestParam String schoolYear,
                             @RequestParam String familySaint,
                             Model model,
                             RedirectAttributes redirectAttributes) {  
                                                            
        try{
        FinalStudent finalStudent = new FinalStudent();
        finalStudent.setLastName(lastName);
        finalStudent.setFirstName(firstName);
        finalStudent.setMiddleName(middleName);
        finalStudent.setSuffix(suffix);
        finalStudent.setBarangay(barangay);
        finalStudent.setMunicipality(municipality);
        finalStudent.setProvince(province);
        finalStudent.setBirthdate(birthdate);
        finalStudent.setBirthPlace(birthPlace);
        finalStudent.setAge(age);
        finalStudent.setExamPlace(examPlace);
        finalStudent.setIdNumber(idNumber);
        finalStudent.setLrn(lrn);
        finalStudent.setReligion(religion);
        finalStudent.setGender(gender);
        finalStudent.setCitizenship(citizenship);
        finalStudent.setElementarySchool(elementarySchool);
        finalStudent.setElemAddress(elemAddress);
        finalStudent.setFatherName(fatherName);
        finalStudent.setFatherOccupation(fatherOccupation);
        finalStudent.setFatherContact(fatherContact);
        finalStudent.setMotherName(motherName);
        finalStudent.setMotherOccupation(motherOccupation);
        finalStudent.setMotherContact(motherContact);
        finalStudent.setGuardianName(guardianName);
        finalStudent.setGuardianRelationship(guardianRelationship);
        finalStudent.setGuardianOccupation(guardianOccupation);
        finalStudent.setGuardianContact(guardianContact);
        finalStudent.setPsastatus(psastatus);
        finalStudent.setBaptismalstatus(baptismalstatus);
        finalStudent.setConfirmationstatus(confirmationstatus);
        finalStudent.setGoodmoralstatus(goodmoralstatus);
        finalStudent.setForm138status(form138status);
        finalStudent.setIndigencystatus(indigencystatus);
        finalStudent.setPhilhealthstatus(philhealthstatus);
        finalStudent.setKasunduansatus(kasunduansatus);
        finalStudent.setPhotoStatus(photoStatus);
        finalStudent.setMedicalrecordstatus(medicalrecordstatus);
        finalStudent.setSchoolYear(schoolYear);
        finalStudent.setFamilySaint(familySaint);

        finalStudentRepository.save(finalStudent);

        redirectAttributes.addFlashAttribute("successMessage", "Student successfully added!");
     } catch (Exception e) {
         // Flash error message
         redirectAttributes.addFlashAttribute("errorMessage", "Failed to save student data: " + e.getMessage());
     }

        return "redirect:/admission";


    }

   

}
