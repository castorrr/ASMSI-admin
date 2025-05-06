package com.asmsi.admin_system.dto;

import java.util.ArrayList;
import java.util.List;

public class FamilySaintGroupDTO {
    private Long settingId;
    private List<String> saints = new ArrayList<>();
    private List<String> motherSisters = new ArrayList<>();
    private List<String> buildings = new ArrayList<>();

    // Getters and setters
    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public List<String> getSaints() {
        return saints;
    }

    public void setSaints(List<String> saints) {
        this.saints = saints;
    }

    public List<String> getMotherSisters() {
        return motherSisters;
    }

    public void setMotherSisters(List<String> motherSisters) {
        this.motherSisters = motherSisters;
    }

    public List<String> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<String> buildings) {
        this.buildings = buildings;
    }
}
