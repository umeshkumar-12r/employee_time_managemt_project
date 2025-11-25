package com.yourname.timemgmt.dao.impl;

import com.yourname.timemgmt.dao.TimeOffRequestDAO;
import com.yourname.timemgmt.model.TimeOffRequest;
import com.yourname.timemgmt.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of TimeOffRequestDAO for CRUD operations on time_off_requests table.
 */
public class TimeOffRequestDAOImpl implements TimeOffRequestDAO {

    /**
     * Adds a new TimeOffRequest to the database and returns the generated ID.
     *
     * @param request the TimeOffRequest object to add
     * @return the auto-generated ID of the inserted request, or -1 if insertion fails
     */
    @Override
    public int addTimeOffRequest(TimeOffRequest request) {
        // SQL insert statement with placeholders for parameters
        String sql = "INSERT INTO time_off_requests (employee_id, start_date, end_date, reason, status) " +
                "VALUES (?, ?, ?, ?, ?)";
        // Use try-with-resources to automatically close resources
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Bind parameters from TimeOffRequest to the insert statement
            ps.setInt(1, request.getEmployeeId());
            ps.setDate(2, Date.valueOf(request.getStartDate()));
            ps.setDate(3, Date.valueOf(request.getEndDate()));
            ps.setString(4, request.getReason());
            ps.setString(5, request.getStatus());

            // Execute the insert, check if it affected rows
            int rows = ps.executeUpdate();
            if (rows > 0) {
                // Retrieve the generated keys (primary key ID)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        request.setId(id); // Set the generated ID on the object
                        return id;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding time off request");
            e.printStackTrace();
        }
        // Return -1 if insertion failed or no ID generated
        return -1;
    }

    /**
     * Retrieves a list of TimeOffRequests for a specific employee, ordered by start date descending.
     *
     * @param employeeId the employee's ID to filter requests
     * @return list of TimeOffRequest objects for the employee (empty if none found)
     */
    @Override
    public List<TimeOffRequest> getRequestsByEmployee(int employeeId) {
        String sql = "SELECT * FROM time_off_requests WHERE employee_id = ? ORDER BY start_date DESC";
        List<TimeOffRequest> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TimeOffRequest req = new TimeOffRequest();
                    req.setId(rs.getInt("id"));
                    req.setEmployeeId(rs.getInt("employee_id"));
                    req.setStartDate(rs.getDate("start_date").toLocalDate());
                    req.setEndDate(rs.getDate("end_date").toLocalDate());
                    req.setReason(rs.getString("reason"));
                    req.setStatus(rs.getString("status"));
                    list.add(req);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching time off requests");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Updates the status of an existing time off request by its ID.
     *
     * @param requestId the ID of the TimeOffRequest to update
     * @param status the new status value (e.g., "Approved", "Denied")
     */
    @Override
    public void updateRequestStatus(int requestId, String status) {
        String sql = "UPDATE time_off_requests SET status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, requestId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating time off request status");
            e.printStackTrace();
        }
    }
}
