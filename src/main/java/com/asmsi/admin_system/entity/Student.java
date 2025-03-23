package com.asmsi.admin_system.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lrn;
    private String idNumber;
    private String name;
    private String birthday;
    private String address;
    private String placeOfBirth;
    private String elementarySchool;
    private String via;
    private String campus;
    private String familySaint;  // New Field
    private String batchNumber;  // New Field
}
