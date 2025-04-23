package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.FamilySaint;
import com.asmsi.admin_system.service.FamilySaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/family-saint-settings")
public class FamilySaintController {

    private final FamilySaintService familySaintService;

    public FamilySaintController(FamilySaintService familySaintService) {
        this.familySaintService = familySaintService;
    }

    // Serve Thymeleaf UI
    @GetMapping
    public String familySaintSettingsPage(Model model) {
        model.addAttribute("familySaints", familySaintService.getBySchoolYear("2024-2025"));
        return "family-saint-settings";
    }

    // Fetch list (used by JS)
    @GetMapping("/api")
    @ResponseBody
    public List<FamilySaint> getFamilySaints(@RequestParam String schoolYear) {
        return familySaintService.getBySchoolYear(schoolYear);
    }

    // Save via JSON (used by JS fetch)
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<FamilySaint> addFamilySaint(@RequestBody FamilySaint familySaint) {
        FamilySaint saved = familySaintService.save(familySaint);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/add")
public String addFamilySaint(
        @RequestParam("schoolYear") String schoolYear,
        @RequestParam("saintName") String saintName,
        RedirectAttributes redirectAttributes
) {
    FamilySaint newSaint = new FamilySaint(schoolYear, saintName);
    familySaintService.save(newSaint);
    redirectAttributes.addFlashAttribute("successMessage", "Family Saint added successfully.");
    return "redirect:/family-saint-settings";
}

}
