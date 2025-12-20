package com.yourname.timemgmt.dao;

import com.yourname.timemgmt.model.TimeOffRequest;
import java.util.List;

/**
 * Data Access Object (DAO) interface for managing
 * Time Off Request persistence operations.
 *
 * This interface defines methods for creating, retrieving,
 * and updating time off requests in the data source.
 */
public interface TimeOffRequestDAO {

    /**
     * Adds a new time off request to the data source.
     *
     * @param request the TimeOffRequest object to be added
     */
    void addRequest(TimeOffRequest request);

    /**
     * Adds a time off request to the data source.
     *
     * Note: This method appears to duplicate addRequest()
     * and may be kept for backward compatibility or
     * removed to avoid redundancy.
     *
     * @param request the TimeOffRequest object to be added
     */
    void addTimeOffRequest(TimeOffRequest request);

    /**
     * Retrieves all time off requests from the data source.
     *
     * @return a list of all TimeOffRequest objects
     */
    List<TimeOffRequest> getAllRequests();

    /**
     * Retrieves all time off requests submitted by a specific employee.
     *
     * @param employeeId the unique ID of the employee
     * @return a list of TimeOffRequest objects for the given employee
     */
    List<TimeOffRequest> getRequestsByEmployee(int employeeId);

    /**
     * Updates the status of an existing time off request.
     *
     * @param requestId the unique ID of the request
     * @param newStatus the new status (e.g., "APPROVED", "REJECTED")
     */
    void updateRequestStatus(int requestId, String newStatus);
}
