package com.yourname.timemgmt.dao.impl;

import com.yourname.timemgmt.dao.WorkEntryDAO;
import com.yourname.timemgmt.model.WorkEntry;
import com.yourname.timemgmt.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of WorkEntryDAO for CRUD operations on work_entries table.
 */
public class WorkEntryDAOImpl implements WorkEntryDAO {

    /**
     * Inserts a new WorkEntry into the database and returns the generated ID.
     *
     * @param entry WorkEntry object to add
     * @return generated ID of the inserted WorkEntry, or -1 if the insertion failed
     */
    @Override
    public int addWorkEntry(WorkEntry entry) {
        // SQL to insert values into work_entries table
        String sql = "INSERT INTO work_entries (employee_id, work_date, hours_worked, description) " +
                "VALUES (?, ?, ?, ?)";
        // Use try-with-resources to auto-close DB resources
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Bind parameters from WorkEntry object to the SQL statement
            ps.setInt(1, entry.getEmployeeId());
            ps.setDate(2, Date.valueOf(entry.getWorkDate()));
            ps.setDouble(3, entry.getHoursWorked());
            ps.setString(4, entry.getDescription());

            // Execute the insert operation and retrieve affected row count
            int rows = ps.executeUpdate();
            if (rows > 0) {
                // Retrieve auto-generated primary key (ID)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        entry.setId(id);  // Set generated ID back to WorkEntry object
                        return id;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding work entry");
            e.printStackTrace();
        }
        // Return -1 if insertion or key retrieval failed
        return -1;
    }

    /**
     * Fetches all WorkEntry records for a given employee, ordered by work_date descending.
     *
     * @param employeeId employee ID to filter work entries
     * @return list of WorkEntry objects, or empty list if none found
     */
    @Override
    public List<WorkEntry> getWorkEntriesByEmployee(int employeeId) {
        String sql = "SELECT * FROM work_entries WHERE employee_id = ? ORDER BY work_date DESC";
        List<WorkEntry> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Bind employee ID parameter
            ps.setInt(1, employeeId);

            // Execute query and iterate through result set to build list of WorkEntry objects
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    WorkEntry entry = new WorkEntry();
                    entry.setId(rs.getInt("id"));
                    entry.setEmployeeId(rs.getInt("employee_id"));
                    entry.setWorkDate(rs.getDate("work_date").toLocalDate());
                    entry.setHoursWorked(rs.getDouble("hours_worked"));
                    entry.setDescription(rs.getString("description"));
                    list.add(entry);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching work entries by employee");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Fetches WorkEntry records for an employee filtered by a date range, ordered by work_date ascending.
     *
     * @param employeeId employee ID to filter work entries
     * @param start start date (inclusive) for filtering
     * @param end end date (inclusive) for filtering
     * @return list of WorkEntry objects matching criteria, or empty list if none found
     */
    @Override
    public List<WorkEntry> getWorkEntriesByEmployeeAndDateRange(int employeeId, LocalDate start, LocalDate end) {
        String sql = "SELECT * FROM work_entries WHERE employee_id = ? AND work_date BETWEEN ? AND ? ORDER BY work_date";
        List<WorkEntry> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Bind parameters: employee ID, start date, end date
            ps.setInt(1, employeeId);
            ps.setDate(2, Date.valueOf(start));
            ps.setDate(3, Date.valueOf(end));

            // Execute query and convert each result row into a WorkEntry object
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    WorkEntry entry = new WorkEntry();
                    entry.setId(rs.getInt("id"));
                    entry.setEmployeeId(rs.getInt("employee_id"));
                    entry.setWorkDate(rs.getDate("work_date").toLocalDate());
                    entry.setHoursWorked(rs.getDouble("hours_worked"));
                    entry.setDescription(rs.getString("description"));
                    list.add(entry);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching work entries in date range");
            e.printStackTrace();
        }
        return list;
    }
}
