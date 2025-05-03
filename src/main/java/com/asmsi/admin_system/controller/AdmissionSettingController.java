package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.AdmissionSetting;
import com.asmsi.admin_system.repository.AdmissionSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
                                    @RequestParam(name = "saints") List<String> saints,
                                    RedirectAttributes redirectAttributes) {
    // Check for duplicate schoolYear
    boolean isDuplicate = repository.findAll().stream()
        .anyMatch(existing -> existing.getSchoolYear().equalsIgnoreCase(schoolYear) && (id == null || !existing.getId().equals(id)));

    if (isDuplicate) {
        redirectAttributes.addFlashAttribute("errorMessage", "School Year already exists!");
        return "redirect:/admission-settings/view";
    }

    AdmissionSetting setting = (id != null)
            ? repository.findById(id).orElse(new AdmissionSetting())
            : new AdmissionSetting();

    setting.setSchoolYear(schoolYear);
    setting.setSaints(saints);

    repository.save(setting);
    redirectAttributes.addFlashAttribute("successMessage", "Admission Settings saved!");
    return "redirect:/admission-settings/view";
}


    // ✅ View all Admission Settings
    @GetMapping("/view")
    public String viewAll(Model model) {
        List<AdmissionSetting> settings = repository.findAll();
        model.addAttribute("settings", settings);
        return "family-saint-settings"; // Thymeleaf template: family-saint-settings.html
    }

    // ✅ Delete Admission Setting
    @PostMapping("/delete/{id}")
    public String deleteSetting(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Deleted successfully!");
        return "redirect:/admission-settings/view";
    }

    // ✅ Optional: Redirect old route
    @GetMapping("/redirect")
    public String redirect() {
        return "redirect:/admission-settings/view";
    }
}
