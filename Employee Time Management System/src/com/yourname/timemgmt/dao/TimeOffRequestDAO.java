package com.yourname.timemgmt.dao;

import com.yourname.timemgmt.model.TimeOffRequest;
import java.util.List;

/**
 * Data Access Object interface for managing TimeOffRequest entities.
 * Provides methods to add new requests, fetch requests by employee,
 * and update the status of existing requests.
 */
public interface TimeOffRequestDAO {

    /**
     * Adds a new time off request to the data store.
     *
     * @param request the TimeOffRequest object to be added
     * @return the ID of the newly created time off request,
     *         or a negative value if the operation fails
     */
    int addTimeOffRequest(TimeOffRequest request);

    /**
     * Retrieves all time off requests associated with a specific employee.
     *
     * @param employeeId the ID of the employee whose requests should be fetched
     * @return a List of TimeOffRequest objects, or an empty list if none found
     */
    List<TimeOffRequest> getRequestsByEmployee(int employeeId);

    /**
     * Updates the status of an existing time off request.
     *
     * @param requestId the ID of the time off request to update
     * @param status    the new status (e.g., "PENDING", "APPROVED", "REJECTED")
     */
    void updateRequestStatus(int requestId, String status);
}
