package com.asmsi.admin_system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idNumber;
    private String lrn;
    private String name;
    private String campus;
    private String familySaint;
    private String batchNumber;
    private String imageUrl;
    private String date;
    private String timeIn;
    private String timeOut;
    private String status;
    private String remarks;
    private String account;

    // Getters and setters or use @Data from Lombok
}
