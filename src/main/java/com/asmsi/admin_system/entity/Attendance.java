package com.asmsi.admin_system.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  

    // Getters and setters or use @Data from Lombok
}
