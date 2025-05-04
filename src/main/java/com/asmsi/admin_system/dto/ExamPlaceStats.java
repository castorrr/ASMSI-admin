package com.asmsi.admin_system.dto;

public class ExamPlaceStats {
    private String examPlace;
    private Long total;
    private Long arrived;

    public ExamPlaceStats(String examPlace, Long total, Long arrived) {
        this.examPlace = examPlace;
        this.total = total;
        this.arrived = arrived;
    }

    public String getExamPlace() {
        return examPlace;
    }

    public Long getTotal() {
        return total;
    }

    public Long getArrived() {
        return arrived;
    }
}
