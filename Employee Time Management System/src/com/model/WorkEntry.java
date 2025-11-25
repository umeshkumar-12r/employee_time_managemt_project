package com.yourname.timemgmt.model;

import java.time.LocalDate;

/**
 * Represents a single work log entry for an employee.
 */
public class WorkEntry {

    // Unique identifier of the work entry (primary key in the database).
    private int id;

    // Identifier of the employee this work entry belongs to.
    private int employeeId;

    // Date on which the work was done.
    private LocalDate workDate;

    // Number of hours worked on the given date.
    private double hoursWorked;

    // Optional description or notes about the work performed.
    private String description;

    // No-argument constructor for frameworks and libraries that require it.
    public WorkEntry() {}

    // Constructor used when creating a new work entry before it has an ID.
    public WorkEntry(int employeeId, LocalDate workDate, double hoursWorked, String description) {
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.hoursWorked = hoursWorked;
        this.description = description;
    }

    // Constructor used when all fields, including ID, are known (e.g., when loading from DB).
    public WorkEntry(int id, int employeeId, LocalDate workDate, double hoursWorked, String description) {
        this.id = id;
        this.employeeId = employeeId;
        this.workDate = workDate;
        this.hoursWorked = hoursWorked;
        this.description = description;
    }

    // Getter and setter methods for all fields.

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public LocalDate getWorkDate() { return workDate; }

    public void setWorkDate(LocalDate workDate) { this.workDate = workDate; }

    public double getHoursWorked() { return hoursWorked; }

    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    /**
     * Returns a string representation of this work entry, useful for logging and debugging.
     */
    @Override
    public String toString() {
        return "WorkEntry{id=" + id +
                ", employeeId=" + employeeId +
                ", workDate=" + workDate +
                ", hoursWorked=" + hoursWorked +
                ", description='" + description + '\'' +
                '}';
    }
}
