package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.Student;
import com.asmsi.admin_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student-info")
    public String showStudentManager(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("student", new Student()); // Bind empty student for modal form
        return "studentmanager";
    }

    @PostMapping("/add-student")
    public String addStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/student-info";
    }
}
