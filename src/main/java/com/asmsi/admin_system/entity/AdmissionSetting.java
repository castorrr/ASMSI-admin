package com.asmsi.admin_system.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "admission_setting")
public class AdmissionSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String schoolYear;

    @OneToMany(mappedBy = "setting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdmissionSaint> admissionSaints;

    @ElementCollection
    @CollectionTable(
        name = "admission_saints",
        joinColumns = @JoinColumn(name = "setting_id")
    )
    @Column(name = "saint_name")
    private List<String> saints;

    @ElementCollection
    @CollectionTable(
        name = "admission_mother_sisters",
        joinColumns = @JoinColumn(name = "setting_id")
    )
    @Column(name = "mother_sister")
    private List<String> motherSisters;

    @ElementCollection
    @CollectionTable(
        name = "admission_buildings",
        joinColumns = @JoinColumn(name = "setting_id")
    )
    @Column(name = "building_name")
    private List<String> buildings;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSchoolYear() { return schoolYear; }
    public void setSchoolYear(String schoolYear) { this.schoolYear = schoolYear; }

    public List<AdmissionSaint> getAdmissionSaints() { return admissionSaints; }
    public void setAdmissionSaints(List<AdmissionSaint> admissionSaints) { this.admissionSaints = admissionSaints; }

    public List<String> getSaints() { return saints; }
    public void setSaints(List<String> saints) { this.saints = saints; }

    public List<String> getMotherSisters() { return motherSisters; }
    public void setMotherSisters(List<String> motherSisters) { this.motherSisters = motherSisters; }

    public List<String> getBuildings() { return buildings; }
    public void setBuildings(List<String> buildings) { this.buildings = buildings; }

    // JSON helpers for Thymeleaf JS parsing
    @Transient
    public String getSaintsJson() {
        return toJson(saints);
    }

    @Transient
    public String getMotherSistersJson() {
        return toJson(motherSisters);
    }

    @Transient
    public String getBuildingsJson() {
        return toJson(buildings);
    }

    private String toJson(List<String> list) {
        try {
            return new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            return "[]";
        }
    }
}
