package com.yourname.timemgmt.dao;

import com.yourname.timemgmt.model.WorkEntry;
import java.time.LocalDate;
import java.util.List;

/**
 * Data Access Object interface for handling WorkEntry data operations.
 * Defines the contract for adding and retrieving work entries linked to employees.
 */
public interface WorkEntryDAO {

    /**
     * Adds a new WorkEntry record to the data store.
     *
     * @param entry the WorkEntry object to add
     * @return the ID of the newly inserted WorkEntry, or a negative value if the operation fails
     */
    int addWorkEntry(WorkEntry entry);

    /**
     * Retrieves all WorkEntry records associated with a given employee ID.
     *
     * @param employeeId the ID of the employee whose work entries should be retrieved
     * @return a list of WorkEntry objects for that employee (empty list if none found)
     */
    List<WorkEntry> getWorkEntriesByEmployee(int employeeId);

    /**
     * Retrieves WorkEntry records for a specific employee filtered by a date range.
     *
     * @param employeeId the ID of the employee
     * @param start the start date of the range (inclusive)
     * @param end the end date of the range (inclusive)
     * @return a list of WorkEntry objects matching the employee and date range criteria
     */
    List<WorkEntry> getWorkEntriesByEmployeeAndDateRange(int employeeId, LocalDate start, LocalDate end);
}
