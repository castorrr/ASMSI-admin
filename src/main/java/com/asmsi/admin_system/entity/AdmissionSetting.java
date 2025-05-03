package com.asmsi.admin_system.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "admission_setting") // match exactly with your table name
public class AdmissionSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String schoolYear;

    @OneToMany(mappedBy = "setting", cascade = CascadeType.ALL)
    private List<AdmissionSaint> admissionSaints;
    
    
    public List<AdmissionSaint> getAdmissionSaints() {
        return admissionSaints;
    }
    
    public void setAdmissionSaints(List<AdmissionSaint> admissionSaints) {
        this.admissionSaints = admissionSaints;
    }
    @ElementCollection
    @CollectionTable(
        name = "admission_saints", // this maps to the secondary table holding the list
        joinColumns = @JoinColumn(name = "setting_id")
    )
    @Column(name = "saint_name")
    private List<String> saints;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSchoolYear() { return schoolYear; }
    public void setSchoolYear(String schoolYear) { this.schoolYear = schoolYear; }

    public List<String> getSaints() { return saints; }
    public void setSaints(List<String> saints) { this.saints = saints; }
}
