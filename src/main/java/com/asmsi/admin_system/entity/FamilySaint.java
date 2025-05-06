package com.asmsi.admin_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "family_saints")
public class FamilySaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_year")
    private String schoolYear;

    @Column(name = "saint_name")
    private String saintName;

    @Column(name = "mother_sister")
    private String motherSister;

    @Column(name = "building_name")
    private String buildingName;

    // Constructors
    public FamilySaint() {}

    public FamilySaint(String schoolYear, String saintName) {
        this.schoolYear = schoolYear;
        this.saintName = saintName;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getSchoolYear() { return schoolYear; }

    public void setSchoolYear(String schoolYear) { this.schoolYear = schoolYear; }

    public String getSaintName() { return saintName; }

    public void setSaintName(String saintName) { this.saintName = saintName; }

   


    public String getMotherSister() { return motherSister; }
public void setMotherSister(String motherSister) { this.motherSister = motherSister; }

public String getBuildingName() { return buildingName; }
public void setBuildingName(String buildingName) { this.buildingName = buildingName; }

}
