package com.asmsi.admin_system.dto;

public class FamilySaintGridEntry {
    private String codeNumber;
    private String studentName;
    private String province;

    public FamilySaintGridEntry() {}

    public FamilySaintGridEntry(String codeNumber, String studentName, String province) {
        this.codeNumber = codeNumber;
        this.studentName = studentName;
        this.province = province;
    }

    public String getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
