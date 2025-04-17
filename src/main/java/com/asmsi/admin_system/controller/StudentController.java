package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.Student;
import com.asmsi.admin_system.repository.StudentRepository;
import com.asmsi.admin_system.service.CloudinaryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.CSVReader;

import java.io.Reader;

import java.util.List;



@Controller
public class StudentController {
    
    @Autowired
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
  

    @PostMapping("/upload-csv")
    public String uploadCSV(@RequestParam("file") MultipartFile file) {
        try (Reader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.ISO_8859_1))) {
    
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> records = csvReader.readAll();
            records.remove(0); // remove header
    
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    
            for (String[] record : records) {
                Student student = new Student();
                student.setAddress(record[1]);
                student.setBirthday(LocalDate.parse(record[2], formatter)); // may fail here
                student.setCampus(record[3]);
                student.setElementarySchool(record[4]);
                student.setIdNumber(record[5]);
                student.setLrn(record[6]);
                student.setName(record[7]);
                student.setPlaceOfBirth(record[8]);
                student.setVia(record[9]);
                student.setFamilySaint(record[10]);
                student.setBatchNumber(record[11]);
                student.setImageUrl(record[12]);
    
                studentRepository.save(student);
            }
    
        } catch (Exception e) {
            System.out.println("❌ Error during CSV upload:");
            e.printStackTrace(); // ✅ This will show the actual cause in your console/logs
            return "redirect:/error"; // or redirect to a page you’ve defined
        }
    
        return "redirect:/students"; // or wherever your view is
    }
    @GetMapping("/students")
    public String studentsPage(Model model) {
        List<Student> students = studentRepository.findAll();
        List<String> vias = studentRepository.findDistinctVias(); // ✅ get unique VIA values
    
        model.addAttribute("students", students); // ✅ clean one
        model.addAttribute("vias", vias);         // ✅ for dropdown
    
        return "studentmanager";
    }
    

    

}
