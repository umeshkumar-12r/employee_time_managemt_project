package com.yourname.timemgmt.model;

import java.time.LocalDate;

public class WorkEntry {

    private int id;
    private int employeeId;
    private double hoursWorked;
    private LocalDate workDate;
    private String description;   //  Added (required by UI)

    //  Required for UI (fixes "no suitable constructor" error)
    public WorkEntry() {
    }

    //  Constructor used by Main and tests
    public WorkEntry(int employeeId, LocalDate workDate, double hoursWorked, String description) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.hoursWorked = hoursWorked;
        this.description = description;
    }

    // Existing constructor kept for DB queries
    public WorkEntry(int id, int employeeId, double hoursWorked, LocalDate workDate) {
        this.id = id;
        this.employeeId = employeeId;
        this.hoursWorked = hoursWorked;
        this.workDate = workDate;
    }

    // ------------------ GETTERS & SETTERS ------------------

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

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public String getDescription() {
        return description;
    }

    // 🔥 Required by UI — fixes "cannot find symbol setDescription"
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WorkEntry{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", hoursWorked=" + hoursWorked +
                ", workDate=" + workDate +
                ", description='" + description + '\'' +
                '}';
    }
}
