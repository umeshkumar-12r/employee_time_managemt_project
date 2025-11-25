package com.yourname.timemgmt.model;

import java.time.LocalDate;

/**
 * Represents a time off request submitted by an employee.
 */
public class TimeOffRequest {

    // Unique identifier for this time off request.
    private int id;

    // The ID of the employee who submitted this request.
    private int employeeId;

    // The start date of the requested time off.
    private LocalDate startDate;

    // The end date of the requested time off.
    private LocalDate endDate;

    // Reason provided by the employee for the time off.
    private String reason;

    // Status of the request such as 'Pending', 'Approved', or 'Denied'.
    private String status;

    /**
     * No-argument constructor required for frameworks.
     */
    public TimeOffRequest() {}

    /**
     * Constructor for creating a new time off request before it has a database ID.
     * @param employeeId Employee's ID submitting the request
     * @param startDate Start date of the time off
     * @param endDate End date of the time off
     * @param reason Reason for the request
     * @param status Status of the request
     */
    public TimeOffRequest(int employeeId, LocalDate startDate, LocalDate endDate,
                          String reason, String status) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    /**
     * Constructor with all fields including the request ID, used when loading from DB.
     * @param id Request ID
     * @param employeeId Employee's ID submitting the request
     * @param startDate Start date of the time off
     * @param endDate End date of the time off
     * @param reason Reason for the request
     * @param status Status of the request
     */
    public TimeOffRequest(int id, int employeeId, LocalDate startDate, LocalDate endDate,
                          String reason, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    // Standard getters and setters for all fields.

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getReason() { return reason; }

    public void setReason(String reason) { this.reason = reason; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    /**
     * Returns a string representation useful for debugging and logging.
     */
    @Override
    public String toString() {
        return "TimeOffRequest{id=" + id +
                ", employeeId=" + employeeId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
