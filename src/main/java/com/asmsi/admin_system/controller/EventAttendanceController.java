package com.asmsi.admin_system.controller;

import com.asmsi.admin_system.entity.EventAttendance;
import com.asmsi.admin_system.repository.EventAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-attendance")
public class EventAttendanceController {

    @Autowired
    private EventAttendanceRepository eventAttendanceRepository;

    @PostMapping("/submit")
    public String submitEventAttendance(@RequestBody EventAttendance attendance) {
        eventAttendanceRepository.save(attendance);
        return "Event Attendance Saved Successfully!";
    }
}
