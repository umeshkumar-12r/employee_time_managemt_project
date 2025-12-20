package com.yourname.timemgmt.dao.impl;

import com.yourname.timemgmt.dao.WorkEntryDAO;
import com.yourname.timemgmt.model.WorkEntry;
import com.yourname.timemgmt.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the WorkEntryDAO interface.
 *
 * This class handles database operations related to
 * employee work entries using JDBC.
 */
public class WorkEntryDAOImpl implements WorkEntryDAO {

    /**
     * Inserts a new work entry into the database.
     *
     * @param workEntry the WorkEntry object containing
     *                  employee ID, hours worked, date, and description
     */
    @Override
    public void addWorkEntry(WorkEntry workEntry) {
        // SQL query to insert a work entry record
        String sql = "INSERT INTO work_entries " +
                "(employee_id, hours_worked, work_date, description) " +
                "VALUES (?, ?, ?, ?)";

        // Try-with-resources ensures proper closing of database resources
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set prepared statement parameters
            ps.setInt(1, workEntry.getEmployeeId());
            ps.setDouble(2, workEntry.getHoursWorked());
            ps.setDate(3, Date.valueOf(workEntry.getWorkDate()));
            ps.setString(4, workEntry.getDescription());

            // Execute insert operation
            ps.executeUpdate();

        } catch (SQLException e) {
            // Log SQL exception
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all work entries for a specific employee.
     *
     * @param empId the unique ID of the employee
     * @return a list of WorkEntry objects for the given employee
     */
    @Override
    public List<WorkEntry> getWorkEntriesByEmployee(int empId) {
        List<WorkEntry> list = new ArrayList<>();

        // SQL query to fetch work entries by employee ID
        String sql = "SELECT id, employee_id, hours_worked, work_date, description " +
                "FROM work_entries WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set employee ID parameter
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            // Process result set
            while (rs.next()) {
                WorkEntry entry = new WorkEntry();

                // Populate WorkEntry object
                entry.setId(rs.getInt("id"));
                entry.setEmployeeId(rs.getInt("employee_id"));
                entry.setHoursWorked(rs.getDouble("hours_worked"));
                entry.setWorkDate(rs.getDate("work_date").toLocalDate());
                entry.setDescription(rs.getString("description"));

                list.add(entry);
            }

        } catch (SQLException e) {
            // Log SQL exception
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Retrieves all work entries from the database.
     *
     * @return a list of all WorkEntry records
     */
    @Override
    public List<WorkEntry> getAllWorkEntries() {
        List<WorkEntry> list = new ArrayList<>();

        // SQL query to fetch all work entries
        String sql = "SELECT id, employee_id, hours_worked, work_date, description " +
                "FROM work_entries";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Process result set
            while (rs.next()) {
                WorkEntry entry = new WorkEntry();

                // Populate WorkEntry object
                entry.setId(rs.getInt("id"));
                entry.setEmployeeId(rs.getInt("employee_id"));
                entry.setHoursWorked(rs.getDouble("hours_worked"));
                entry.setWorkDate(rs.getDate("work_date").toLocalDate());
                entry.setDescription(rs.getString("description"));

                list.add(entry);
            }

        } catch (SQLException e) {
            // Log SQL exception
            e.printStackTrace();
        }

        return list;
    }
}
