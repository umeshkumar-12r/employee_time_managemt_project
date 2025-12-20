package com.yourname.timemgmt.dao.impl;

import com.yourname.timemgmt.dao.TimeOffRequestDAO;
import com.yourname.timemgmt.model.TimeOffRequest;
import com.yourname.timemgmt.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the TimeOffRequestDAO interface.
 *
 * This class handles all database operations related to
 * time off requests using JDBC.
 */
public class TimeOffRequestDAOImpl implements TimeOffRequestDAO {

    /**
     * Adds a new time off request.
     *
     * This method forwards the call to addTimeOffRequest()
     * to maintain compatibility with older code.
     *
     * @param request the TimeOffRequest object to be added
     */
    @Override
    public void addRequest(TimeOffRequest request) {
        addTimeOffRequest(request); // Forward to main insert method
    }

    /**
     * Inserts a time off request into the database.
     *
     * @param request the TimeOffRequest object containing
     *                employee ID, dates, status, and reason
     */
    @Override
    public void addTimeOffRequest(TimeOffRequest request) {
        // SQL query to insert a new time off request
        String sql = "INSERT INTO time_off_requests " +
                "(employee_id, start_date, end_date, status, reason) " +
                "VALUES (?, ?, ?, ?, ?)";

        // Try-with-resources ensures automatic resource cleanup
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set prepared statement parameters
            ps.setInt(1, request.getEmployeeId());
            ps.setDate(2, Date.valueOf(request.getStartDate()));
            ps.setDate(3, Date.valueOf(request.getEndDate()));
            ps.setString(4, request.getStatus());
            ps.setString(5, request.getReason());

            // Execute insert operation
            ps.executeUpdate();

        } catch (SQLException e) {
            // Log SQL exception
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all time off requests from the database.
     *
     * @return a list of all TimeOffRequest objects
     */
    @Override
    public List<TimeOffRequest> getAllRequests() {
        List<TimeOffRequest> list = new ArrayList<>();

        // SQL query to fetch all time off requests
        String sql = "SELECT id, employee_id, start_date, end_date, status, reason " +
                "FROM time_off_requests";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Process result set
            while (rs.next()) {
                TimeOffRequest req = new TimeOffRequest();

                // Populate TimeOffRequest object
                req.setId(rs.getInt("id"));
                req.setEmployeeId(rs.getInt("employee_id"));
                req.setStartDate(rs.getDate("start_date").toLocalDate());
                req.setEndDate(rs.getDate("end_date").toLocalDate());
                req.setStatus(rs.getString("status"));
                req.setReason(rs.getString("reason"));

                list.add(req);
            }

        } catch (SQLException e) {
            // Log SQL exception
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Retrieves time off requests for a specific employee.
     *
     * @param employeeId the unique ID of the employee
     * @return a list of TimeOffRequest objects for the employee
     */
    @Override
    public List<TimeOffRequest> getRequestsByEmployee(int employeeId) {
        List<TimeOffRequest> list = new ArrayList<>();

        // SQL query to fetch requests for a specific employee
        String sql = "SELECT id, employee_id, start_date, end_date, status, reason " +
                "FROM time_off_requests WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set employee ID parameter
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            // Process result set
            while (rs.next()) {
                TimeOffRequest req = new TimeOffRequest(
                        rs.getInt("id"),
                        rs.getInt("employee_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getString("status"),
                        rs.getString("reason")
                );
                list.add(req);
            }

        } catch (SQLException e) {
            // Log SQL exception
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Updates the status of an existing time off request.
     *
     * @param requestId the ID of the request to update
     * @param newStatus the new status (e.g., APPROVED, REJECTED)
     */
    @Override
    public void updateRequestStatus(int requestId, String newStatus) {
        // SQL query to update request status
        String sql = "UPDATE time_off_requests SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set parameters
            ps.setString(1, newStatus);
            ps.setInt(2, requestId);

            // Execute update
            ps.executeUpdate();

        } catch (SQLException e) {
            // Log SQL exception
            e.printStackTrace();
        }
    }
}
