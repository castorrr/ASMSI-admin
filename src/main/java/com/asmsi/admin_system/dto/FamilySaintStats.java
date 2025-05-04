package com.asmsi.admin_system.dto;

import java.util.List;

public class FamilySaintStats {
    private String familySaint;
    private Long totalStudents;
    private List<Integer> usedCodeNumbers;

    public FamilySaintStats(String familySaint, Long totalStudents, List<Integer> usedCodeNumbers) {
        this.familySaint = familySaint;
        this.totalStudents = totalStudents;
        this.usedCodeNumbers = usedCodeNumbers;
    }

    // Getters
    public String getFamilySaint() { return familySaint; }
    public Long getTotalStudents() { return totalStudents; }
    public List<Integer> getUsedCodeNumbers() { return usedCodeNumbers; }
}
