package com.yourname.timemgmt.model;

import java.time.LocalDate;

public class TimeOffRequest {

    private int id;
    private int employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String reason;

    public TimeOffRequest() {
    }

    // Constructor required by Main.java
    public TimeOffRequest(int employeeId, LocalDate startDate, LocalDate endDate,
                          String reason, String status) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    // Constructor used when reading from DB
    public TimeOffRequest(int id, int employeeId, LocalDate startDate,
                          LocalDate endDate, String status, String reason) {
        this.id = id;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "TimeOffRequest{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
