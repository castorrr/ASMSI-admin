package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.AdmissionSaint;
import com.asmsi.admin_system.entity.AdmissionSetting;
import com.asmsi.admin_system.repository.AdmissionSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admission-settings")
public class AdmissionSettingController {

    @Autowired
    private AdmissionSettingRepository repository;

    // ✅ Save or Update Admission Setting
    @PostMapping("/save")
    public String saveAdmissionSettings(@RequestParam(required = false) Long id,
                                        @RequestParam String schoolYear,
                                        @RequestParam List<String> saints,
                                        @RequestParam List<String> motherSisters,
                                        @RequestParam List<String> buildings,
                                        RedirectAttributes redirectAttributes) {

        boolean isDuplicate = repository.findAll().stream()
                .anyMatch(existing -> existing.getSchoolYear().equalsIgnoreCase(schoolYear)
                        && (id == null || !existing.getId().equals(id)));

        if (isDuplicate) {
            redirectAttributes.addFlashAttribute("errorMessage", "School Year already exists!");
            return "redirect:/admission-settings/view";
        }

        AdmissionSetting setting = (id != null)
                ? repository.findById(id).orElse(new AdmissionSetting())
                : new AdmissionSetting();

        setting.setSchoolYear(schoolYear);
        setting.setSaints(saints);
        setting.setMotherSisters(motherSisters);
        setting.setBuildings(buildings);

        repository.save(setting);
        redirectAttributes.addFlashAttribute("successMessage", "Admission setting saved!");
        return "redirect:/admission-settings/view";
    }


    // ✅ View all Admission Settings
    @GetMapping("/view")
    public String viewAll(Model model) {
        List<AdmissionSetting> settings = repository.findAll();
        model.addAttribute("settings", settings);
        return "family-saint-settings"; // Thymeleaf template: family-saint-settings.html
    }

   

    @PostMapping("/delete/{id}")
    @Transactional
    public String forceDelete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (repository.existsById(id)) {
            repository.deleteSaintsBySettingId(id);
            repository.deleteMotherSistersBySettingId(id);
            repository.deleteBuildingsBySettingId(id);
            repository.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Deleted using force delete.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "No such setting found.");
        }
    
        return "redirect:/admission-settings/view";
    }
    
    // ✅ Optional: Redirect old route
    @GetMapping("/redirect")
    public String redirect() {
        return "redirect:/admission-settings/view";
    }

    @Autowired
private AdmissionSettingRepository admissionSettingRepository;


@PostMapping("/admission-settings/save")
public String saveSetting(@RequestParam(required = false) Long id,
                          @RequestParam String schoolYear,
                          @RequestParam List<String> saints,
                          @RequestParam List<String> motherSisters,
                          @RequestParam List<String> buildings,
                          RedirectAttributes redirectAttributes) {

    AdmissionSetting setting = (id != null) ?
            admissionSettingRepository.findById(id).orElse(new AdmissionSetting()) :
            new AdmissionSetting();

    setting.setSchoolYear(schoolYear);
    setting.setSaints(saints);
    setting.setMotherSisters(motherSisters);
    setting.setBuildings(buildings);

    admissionSettingRepository.save(setting);
    redirectAttributes.addFlashAttribute("successMessage", "Admission setting saved!");

    return "redirect:/admission-settings";
}





}
