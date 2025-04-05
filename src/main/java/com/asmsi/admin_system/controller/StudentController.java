package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.Student;
import com.asmsi.admin_system.repository.StudentRepository;
import com.asmsi.admin_system.service.CloudinaryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;
    private final CloudinaryService cloudinaryService;

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

    @PostMapping("/edit-student")
    public String editStudent(
            @ModelAttribute Student student,
            @RequestParam("imageFile") MultipartFile imageFile) {

        try {
            Student existingStudent = studentRepository.findById(student.getId()).orElse(null);

            if (existingStudent == null) {
                // Optional: Add error handling or redirect to error page
                return "redirect:/student-info?error=notfound";
            }

            // If a new image is uploaded
            if (imageFile != null && !imageFile.isEmpty()) {
                String imageUrl = cloudinaryService.uploadImage(imageFile);
                student.setImageUrl(imageUrl);
            } else {
                // Retain the existing image if no new one is uploaded
                student.setImageUrl(existingStudent.getImageUrl());
            }

            // Save the updated student
            studentRepository.save(student);

        } catch (IOException e) {
            e.printStackTrace(); // Optional: Replace with logger
            return "redirect:/student-info?error=upload";
        }

        return "redirect:/student-info";
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam("id") Long id) {
        studentRepository.deleteById(id);
        return "redirect:/student-info";
    }

}
