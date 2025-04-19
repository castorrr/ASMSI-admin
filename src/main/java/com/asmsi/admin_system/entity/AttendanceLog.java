package com.asmsi.admin_system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance_logs")
public class AttendanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idNumber;
    private String lrn;
    private String name;
    private String campus;
    private String familySaint;
    private String batchNumber;
    private String imageUrl;

    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;

    private String status;
    private String remarks;
    private String account;

    // Getters and Setters (You can generate this in your IDE or use Lombok)
    // Example:
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }

    public String getLrn() { return lrn; }
    public void setLrn(String lrn) { this.lrn = lrn; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCampus() { return campus; }
    public void setCampus(String campus) { this.campus = campus; }

    public String getFamilySaint() { return familySaint; }
    public void setFamilySaint(String familySaint) { this.familySaint = familySaint; }

    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTimeIn() { return timeIn; }
    public void setTimeIn(LocalTime timeIn) { this.timeIn = timeIn; }

    public LocalTime getTimeOut() { return timeOut; }
    public void setTimeOut(LocalTime timeOut) { this.timeOut = timeOut; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
}
