package com.asmsi.admin_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "event_attendance")
@Data
public class EventAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;
    private String eventName;
    private String studentId;
    private String name;
    private String lrn;
    private String campus;
    private String familySaint;
    private String batchNumber;
    private String imageUrl;
    private LocalDate date;
    private LocalTime timeIn;
    private String recordedBy;
}
