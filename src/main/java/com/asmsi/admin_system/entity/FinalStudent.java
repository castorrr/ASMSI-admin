package com.asmsi.admin_system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "final_students")
public class FinalStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lastName;
    private String firstName;
    private String middleName;
    private String suffix;
    private String barangay;
    private String municipality;
    private String province;
    private LocalDate birthdate;
    private String birthPlace;
    private int age;
    private String examPlace;
    private String idNumber;
    private String lrn;
    private String religion;
    private String gender;
    private String citizenship;
    private String elementarySchool;
    private String elemAddress;
    private String fatherName;
    private String fatherOccupation;
    private String fatherContact;
    private String motherName;
    private String motherOccupation;
    private String motherContact;
    private String guardianName;
    private String guardianRelationship;
    private String guardianOccupation;
    private String guardianContact;
    private String psastatus;
    private String baptismalstatus;
    private String confirmationstatus;
    private String goodmoralstatus;
    private String form138status;
    private String indigencystatus;
    private String philhealthstatus;
    private String kasunduansatus;
    private String photoStatus;
    private String medicalrecordstatus;
    private String schoolYear;
    private String familySaint;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getExamPlace() {
        return examPlace;
    }

    public void setExamPlace(String examPlace) {
        this.examPlace = examPlace;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getLrn() {
        return lrn;
    }

    public void setLrn(String lrn) {
        this.lrn = lrn;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getElementarySchool() {
        return elementarySchool;
    }

    public void setElementarySchool(String elementarySchool) {
        this.elementarySchool = elementarySchool;
    }

    public String getElemAddress() {
        return elemAddress;
    }

    public void setElemAddress(String elemAddress) {
        this.elemAddress = elemAddress;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherOccupation() {
        return fatherOccupation;
    }

    public void setFatherOccupation(String fatherOccupation) {
        this.fatherOccupation = fatherOccupation;
    }

    public String getFatherContact() {
        return fatherContact;
    }

    public void setFatherContact(String fatherContact) {
        this.fatherContact = fatherContact;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherOccupation() {
        return motherOccupation;
    }

    public void setMotherOccupation(String motherOccupation) {
        this.motherOccupation = motherOccupation;
    }

    public String getMotherContact() {
        return motherContact;
    }

    public void setMotherContact(String motherContact) {
        this.motherContact = motherContact;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianRelationship() {
        return guardianRelationship;
    }

    public void setGuardianRelationship(String guardianRelationship) {
        this.guardianRelationship = guardianRelationship;
    }

    public String getGuardianOccupation() {
        return guardianOccupation;
    }

    public void setGuardianOccupation(String guardianOccupation) {
        this.guardianOccupation = guardianOccupation;
    }

    public String getGuardianContact() {
        return guardianContact;
    }

    public void setGuardianContact(String guardianContact) {
        this.guardianContact = guardianContact;
    }

    public String getPsastatus() {
        return psastatus;
    }

    public void setPsastatus(String psastatus) {
        this.psastatus = psastatus;
    }

    public String getBaptismalstatus() {
        return baptismalstatus;
    }

    public void setBaptismalstatus(String baptismalstatus) {
        this.baptismalstatus = baptismalstatus;
    }

    public String getConfirmationstatus() {
        return confirmationstatus;
    }

    public void setConfirmationstatus(String confirmationstatus) {
        this.confirmationstatus = confirmationstatus;
    }

    public String getGoodmoralstatus() {
        return goodmoralstatus;
    }

    public void setGoodmoralstatus(String goodmoralstatus) {
        this.goodmoralstatus = goodmoralstatus;
    }

    public String getForm138status() {
        return form138status;
    }

    public void setForm138status(String form138status) {
        this.form138status = form138status;
    }

    public String getIndigencystatus() {
        return indigencystatus;
    }

    public void setIndigencystatus(String indigencystatus) {
        this.indigencystatus = indigencystatus;
    }

    public String getPhilhealthstatus() {
        return philhealthstatus;
    }

    public void setPhilhealthstatus(String philhealthstatus) {
        this.philhealthstatus = philhealthstatus;
    }

    public String getKasunduansatus() {
        return kasunduansatus;
    }

    public void setKasunduansatus(String kasunduansatus) {
        this.kasunduansatus = kasunduansatus;
    }

    public String getPhotoStatus() {
        return photoStatus;
    }

    public void setPhotoStatus(String photoStatus) {
        this.photoStatus = photoStatus;
    }

    public String getMedicalrecordstatus() {
        return medicalrecordstatus;
    }

    public void setMedicalrecordstatus(String medicalrecordstatus) {
        this.medicalrecordstatus = medicalrecordstatus;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getFamilySaint() {
        return familySaint;
    }

    public void setFamilySaint(String familySaint) {
        this.familySaint = familySaint;
    }
}

