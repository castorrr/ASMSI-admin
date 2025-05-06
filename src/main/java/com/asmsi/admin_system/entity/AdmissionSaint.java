package com.asmsi.admin_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admission_saints")
public class AdmissionSaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "saint_name")
    private String saintName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setting_id", nullable = false)
    private AdmissionSetting setting;

    private String motherSister;
private String buildingName;

public String getMotherSister() {
    return motherSister;
}

public void setMotherSister(String motherSister) {
    this.motherSister = motherSister;
}

public String getBuildingName() {
    return buildingName;
}

public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
}


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaintName() {
        return saintName;
    }

    public void setSaintName(String saintName) {
        this.saintName = saintName;
    }

    public AdmissionSetting getSetting() {
        return setting;
    }

    public void setSetting(AdmissionSetting setting) {
        this.setting = setting;
    }
}
