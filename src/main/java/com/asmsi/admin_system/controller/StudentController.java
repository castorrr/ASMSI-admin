package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.Student;
import com.asmsi.admin_system.repository.StudentRepository;
import com.asmsi.admin_system.service.CloudinaryService;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public StudentController(StudentRepository studentRepository, CloudinaryService cloudinaryService) {
        this.studentRepository = studentRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/student-info")
    public String showStudentManager(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("student", new Student()); // Bind empty student for modal form
        return "studentmanager";
    }

    @PostMapping("/add-student")
    public String addStudent(@ModelAttribute Student student, 
                             @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            // Upload image to Cloudinary
            if (!imageFile.isEmpty()) {
                String imageUrl = cloudinaryService.uploadImage(imageFile);
                student.setImageUrl(imageUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        studentRepository.save(student);
        return "redirect:/student-info"; // Corrected redirect path
    }
}
