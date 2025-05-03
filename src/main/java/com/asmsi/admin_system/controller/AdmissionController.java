package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.AdmissionData;
import com.asmsi.admin_system.entity.AdmissionSaint;
import com.asmsi.admin_system.entity.AdmissionSetting;
import com.asmsi.admin_system.entity.FinalStudent;
import com.asmsi.admin_system.repository.AdmissionSettingRepository;
import com.asmsi.admin_system.repository.FinalStudentRepository;
import com.asmsi.admin_system.service.AdmissionService;
import com.asmsi.admin_system.service.UploadService;
import com.asmsi.admin_system.service.XmlParserHelper;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.dao.DataIntegrityViolationException;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;


@Controller
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;
    @Autowired
private AdmissionSettingRepository admissionSettingRepository;


    @Autowired
    private FinalStudentRepository finalStudentRepository;

    @Autowired
    private UploadService uploadService;

    // 👉 Admission page
    @GetMapping("/admission")
public String admissionPage(Model model, @ModelAttribute("formData") AdmissionData formData,
                            @ModelAttribute("schoolYear") String schoolYear) {

    model.addAttribute("activePage", "admission");

    // 🟡 Only set 'studentData' if redirected with formData (for field preservation)
    if (formData != null && formData.getIdNumber() != null) {
        model.addAttribute("studentData", formData); // for th:value in inputs
    }

    // 🟡 Preserve selected school year in dropdown if redirected
    if (schoolYear != null && !schoolYear.isEmpty()) {
        model.addAttribute("schoolYear", schoolYear);
    }

    return "admission";
}

    // 👉 Upload Student Database page (includes upload form + summary)
    @GetMapping("/upload-database")
    public String uploadDatabasePage(Model model) {
        List<Map<String, Object>> summaryList = uploadService.getUploadSummary();
        model.addAttribute("studentCounts", summaryList); // for the summary table
        model.addAttribute("activePage", "upload-database"); // for sidebar highlighting
        return "upload-database"; // the Thymeleaf template name
    }

    // 👉 Handle XML upload
    @PostMapping("/upload-xml")
    public String handleXmlUpload(@RequestParam("schoolYear") String schoolYear,
                                  @RequestParam("xmlFile") MultipartFile file,
                                  RedirectAttributes redirectAttributes) {
        try {
            List<AdmissionData> dataList = XmlParserHelper.parse(file, schoolYear);
            admissionService.saveAll(dataList);
            redirectAttributes.addFlashAttribute("successMessage", "XML file successfully uploaded and saved!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to process XML file: " + e.getMessage());
        }
        return "redirect:/upload-database";
    }

    @PostMapping("/submit")
    
@PreAuthorize("hasRole('ADMIN')")
public String submitForm(
     @RequestParam String status,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp,
    @RequestParam String lastName,
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
    @RequestParam String codeNumber,
    RedirectAttributes redirectAttributes,
    Model model // ✅ Add this
) {
    try {
        AdmissionData admissionData = admissionService.searchStudents(idNumber)
            .stream().findFirst().orElse(new AdmissionData());

        boolean isNew = admissionData.getId() == null;
        admissionData.setIdNum(idNumber);
        admissionData.setLastName(lastName);
        admissionData.setFirstName(firstName);
        admissionData.setMiddleName(middleName);
        admissionData.setSuffix(suffix);
        admissionData.setBrgy(barangay);
        admissionData.setMunicipality(municipality);
        admissionData.setProvince(province);
        admissionData.setBirthDate(birthdate);
        admissionData.setBirthPlace(birthPlace);
        admissionData.setAge(age);
        admissionData.setExamPlace(examPlace);
        admissionData.setIdNumber(idNumber);
        admissionData.setLrn(lrn);
        admissionData.setReligion(religion);
        admissionData.setGender(gender);
        admissionData.setCitizenship(citizenship);
        admissionData.setElemSchool(elementarySchool);
        admissionData.setSchoolAddress(elemAddress);
        admissionData.setFatherName(fatherName);
        admissionData.setFatherOccupation(fatherOccupation);
        admissionData.setFatherContact(fatherContact);
        admissionData.setMotherName(motherName);
        admissionData.setMotherOccupation(motherOccupation);
        admissionData.setMotherContact(motherContact);
        admissionData.setGuardianName(guardianName);
        admissionData.setGuardianRelationship(guardianRelationship);
        admissionData.setGuardianOccupation(guardianOccupation);
        admissionData.setGuardianContact(guardianContact);
        admissionData.setPsastatus(psastatus);
        admissionData.setBaptismalstatus(baptismalstatus);
        admissionData.setConfirmationstatus(confirmationstatus);
        admissionData.setGoodmoralstatus(goodmoralstatus);
        admissionData.setForm138status(form138status);
        admissionData.setIndigencystatus(indigencystatus);
        admissionData.setPhilhealthstatus(philhealthstatus);
        admissionData.setKasunduansatus(kasunduansatus);
        admissionData.setPhotoStatus(photoStatus);
        admissionData.setMedicalrecordstatus(medicalrecordstatus);
        admissionData.setSchoolYear(schoolYear);
        admissionData.setFamilySaint(familySaint);
        admissionData.setCodeNumber(Integer.parseInt(codeNumber));
        admissionData.setStatus(status);
        admissionData.setTimestamp(timestamp);

        // ✅ Check for duplicates
        boolean isDuplicate = admissionService.getAllAdmissions().stream()
            .anyMatch(existing ->
                schoolYear.equals(existing.getSchoolYear()) &&
                familySaint.equalsIgnoreCase(existing.getFamilySaint()) &&
                Integer.parseInt(codeNumber) == existing.getCodeNumber() &&
                !idNumber.equals(existing.getIdNum())
            );

            if (isDuplicate) {
                redirectAttributes.addFlashAttribute("errorMessage", "Code number already taken for this Family Saint and School Year.");
                redirectAttributes.addFlashAttribute("stayOnStep", 3); // stay on Step 3
            
                // Preserve all form input (autofill on reload)
                redirectAttributes.addFlashAttribute("formData", admissionData);
                redirectAttributes.addFlashAttribute("schoolYear", schoolYear);
            
                return "redirect:/admission";
            }

        admissionService.save(admissionData);
        redirectAttributes.addFlashAttribute("successMessage", isNew ? "New student record added!" : "Student record updated!");
        return "redirect:/admission";

    } catch (Exception e) {
        model.addAttribute("errorMessage", "Failed to save student: " + e.getMessage());
        model.addAttribute("stayOnStep", 3);
        return "admission";
    }
}

    
    // 👉 Reports page with filters
   

    // 👉 Search API for admission data by ID number
    @GetMapping("/api/admission/search")
@ResponseBody
public ResponseEntity<?> searchById(@RequestParam("idNumber") String idNum) {
    try {
        List<AdmissionData> data = admissionService.searchStudents(idNum);
        System.out.println("Found: " + data.size() + " records");
        return ResponseEntity.ok(data);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500).body(Map.of("error", e.getMessage()));
    }
}

@GetMapping("/api/admission/school-years")
@ResponseBody
public List<String> getSchoolYears() {
    return admissionSettingRepository.findAllSchoolYears();
}


@GetMapping("/family-saint-settings/api")
@ResponseBody
public List<Map<String, String>> getSaintsByYear(@RequestParam("schoolYear") String schoolYear) {
    AdmissionSetting setting = admissionSettingRepository.findBySchoolYear(schoolYear);
    if (setting == null || setting.getAdmissionSaints() == null) return Collections.emptyList();

    return setting.getAdmissionSaints().stream()
        .map(s -> Map.of("saintName", s.getSaintName()))
        .collect(Collectors.toList());
}
@GetMapping("/api/admission/used-codes")
@ResponseBody
public List<Integer> getUsedCodesForSaint(@RequestParam("schoolYear") String schoolYear,
                                          @RequestParam("familySaint") String familySaint) {
    List<FinalStudent> students = finalStudentRepository.findBySchoolYearAndFamilySaint(schoolYear, familySaint);
    return students.stream()
                   .map(FinalStudent::getCodeNumber)
                   .distinct()
                   .collect(Collectors.toList());
}

    
}
