package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.AttendanceLog;
import com.asmsi.admin_system.entity.Student;
import com.asmsi.admin_system.repository.AttendanceLogRepository;
import com.asmsi.admin_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/attendance")
public class AttendanceApiController {

    @Autowired
    private AttendanceLogRepository attendanceLogRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/submit")
public ResponseEntity<String> submitAttendance(@RequestBody AttendanceLog log) {
    String idNumber = log.getIdNumber();
    LocalDate today = LocalDate.now();

    Optional<AttendanceLog> latestLogOpt = attendanceLogRepository
        .findTopByIdNumberAndDateOrderByTimeInDesc(idNumber, today);

    // ⏱ If there's an existing log today with no timeout → record timeout instead
    if (latestLogOpt.isPresent() && latestLogOpt.get().getTimeOut() == null) {
        AttendanceLog latestLog = latestLogOpt.get();
        latestLog.setTimeOut(LocalTime.now());
        attendanceLogRepository.save(latestLog);
        return ResponseEntity.ok("Time Out recorded.");
    }

    // ✅ ELSE: Proceed with your original time-in code
    if (log.getDate() == null) {
        log.setDate(LocalDate.now());
    }
    if (log.getTimeIn() == null) {
        log.setTimeIn(LocalTime.now());
    }

    attendanceLogRepository.save(log);
    return ResponseEntity.ok("Attendance recorded.");
}


    @GetMapping("/logs/{idNumber}")
    public List<AttendanceLog> getLogsByIdNumber(@PathVariable String idNumber) {
        return attendanceLogRepository.findByIdNumber(idNumber);
    }

    @GetMapping("/student/{idNumber}")
    public ResponseEntity<Student> fetchStudentData(@PathVariable String idNumber) {
        Optional<Student> student = studentRepository.findByIdNumber(idNumber);
        return student.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/attendance")
public String attendancePage(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth != null && auth.isAuthenticated()) {
        String username = auth.getName();
        String role = auth.getAuthorities().stream()
                          .findFirst()
                          .map(Object::toString)
                          .orElse("ROLE_USER");

        model.addAttribute("username", username); // ✅ REQUIRED!
        model.addAttribute("role", role);         // ✅ Optional if used
    } else {
        model.addAttribute("username", "Guest");
        model.addAttribute("role", "Unknown");
    }

    return "attendance"; // Must match your attendance.html filename
}

@GetMapping("/logs")
public ResponseEntity<List<AttendanceLog>> getAllLogs() {
    List<AttendanceLog> logs = attendanceLogRepository.findAll(Sort.by(Sort.Direction.DESC, "date", "timeIn"));
    return ResponseEntity.ok(logs);
}



}
