package com.asmsi.admin_system.dto;


public class FamilySaintDTO {
    private String saintName;
    private String motherSister;
    private String buildingName;

    public FamilySaintDTO(String saintName, String motherSister, String buildingName) {
        this.saintName = saintName;
        this.motherSister = motherSister;
        this.buildingName = buildingName;
    }

    // Getters and setters...



    public String getSaintName() {
        return saintName;
    }

    public void setSaintName(String saintName) {
        this.saintName = saintName;
    }

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
}
