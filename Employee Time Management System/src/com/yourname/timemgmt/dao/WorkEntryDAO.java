package com.yourname.timemgmt.dao;

import com.yourname.timemgmt.model.WorkEntry;
import java.util.List;

/**
 * Data Access Object (DAO) interface for managing
 * WorkEntry persistence operations.
 *
 * This interface defines methods for creating and
 * retrieving work entry records from the data source.
 */
public interface WorkEntryDAO {

    /**
     * Adds a new work entry to the data source.
     *
     * @param entry the WorkEntry object containing
     *              employee work details
     */
    void addWorkEntry(WorkEntry entry);

    /**
     * Retrieves all work entries from the data source.
     *
     * @return a list of all WorkEntry records
     */
    List<WorkEntry> getAllWorkEntries();

    /**
     * Retrieves all work entries associated with
     * a specific employee.
     *
     * @param empId the unique ID of the employee
     * @return a list of WorkEntry objects for the given employee
     */
    List<WorkEntry> getWorkEntriesByEmployee(int empId);
}
