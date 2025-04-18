package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.Attendance;
import com.asmsi.admin_system.entity.Student;
import com.asmsi.admin_system.repository.AttendanceRepository;
import com.asmsi.admin_system.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceRestController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping("/student/{idNumber}")
    public ResponseEntity<Student> getStudentByIdNumber(@PathVariable String idNumber) {
        Student student = studentRepository.findByIdNumber(idNumber);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitAttendance(@RequestBody Attendance attendance) {
        attendanceRepository.save(attendance);
        return ResponseEntity.ok("Attendance saved successfully.");
    }
}
