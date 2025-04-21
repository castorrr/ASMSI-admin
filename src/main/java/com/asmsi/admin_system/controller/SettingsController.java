package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.ViaEntry;
import com.asmsi.admin_system.repository.ViaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.List;

@Controller
public class SettingsController {

    @Autowired
    private ViaRepository viaRepository;

    @GetMapping("/via-settings")
    public String showViaSettings(Model model) {
        List<ViaEntry> vias = viaRepository.findAll();
        model.addAttribute("vias", vias);
        model.addAttribute("activePage", "via-settings");
        return "studentvia"; // make sure your html file is studentvia.html
    }

    @PostMapping("/delete-via")
public String deleteVia(@RequestParam Long id) {
    viaRepository.deleteById(id);
    return "redirect:/via-settings";
}

@PostMapping("/edit-via")
public String editVia(@ModelAttribute ViaEntry viaEntry) {
    viaRepository.save(viaEntry); // automatically updates if ID exists
    return "redirect:/via-settings";
}

@PostMapping("/upload-via")
public String handleCsvUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        String line;
        boolean firstLine = true;

        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue; // Skip header row
            }

            // Handles quoted strings properly using split + regex
            String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            if (parts.length >= 3) { // id, place, via
                String place = parts[1].replace("\"", "").trim(); // remove quotes if any
                String via = parts[2].replace("\"", "").trim();

                ViaEntry entry = new ViaEntry();
                entry.setPlace(place);
                entry.setVia(via);
                viaRepository.save(entry);
            }
        }

        redirectAttributes.addFlashAttribute("message", "CSV uploaded successfully!");
    } catch (IOException e) {
        redirectAttributes.addFlashAttribute("message", "Error uploading CSV: " + e.getMessage());
    }

    return "redirect:/via-settings";
}


}
