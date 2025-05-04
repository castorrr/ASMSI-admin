package com.asmsi.admin_system.repository;


public class FamilySaintGridEntry {
    private int codeNumber;
    private String studentName;
    private String province;

    // Constructors
    public FamilySaintGridEntry() {}

    public FamilySaintGridEntry(int codeNumber, String studentName, String province) {
        this.codeNumber = codeNumber;
        this.studentName = studentName;
        this.province = province;
    }

    // Getters and Setters
    public int getCodeNumber() { return codeNumber; }
    public void setCodeNumber(int codeNumber) { this.codeNumber = codeNumber; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
}