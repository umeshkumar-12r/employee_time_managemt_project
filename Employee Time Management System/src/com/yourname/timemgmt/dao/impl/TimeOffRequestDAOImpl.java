package com.yourname.timemgmt.dao.impl;

import com.yourname.timemgmt.dao.TimeOffRequestDAO;
import com.yourname.timemgmt.model.TimeOffRequest;
import com.yourname.timemgmt.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeOffRequestDAOImpl implements TimeOffRequestDAO {

    // OLD METHOD — keep it
    @Override
    public void addRequest(TimeOffRequest request) {
        addTimeOffRequest(request); // Forward to correct method
    }

    // NEW METHOD — required by Main.java
    @Override
    public void addTimeOffRequest(TimeOffRequest request) {
        String sql = "INSERT INTO time_off_requests (employee_id, start_date, end_date, status, reason) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, request.getEmployeeId());
            ps.setDate(2, Date.valueOf(request.getStartDate()));
            ps.setDate(3, Date.valueOf(request.getEndDate()));
            ps.setString(4, request.getStatus());
            ps.setString(5, request.getReason());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // OLD METHOD — keep it
    @Override
    public List<TimeOffRequest> getAllRequests() {
        List<TimeOffRequest> list = new ArrayList<>();
        String sql = "SELECT id, employee_id, start_date, end_date, status, reason FROM time_off_requests";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TimeOffRequest req = new TimeOffRequest();
                req.setId(rs.getInt("id"));
                req.setEmployeeId(rs.getInt("employee_id"));
                req.setStartDate(rs.getDate("start_date").toLocalDate());
                req.setEndDate(rs.getDate("end_date").toLocalDate());
                req.setStatus(rs.getString("status"));
                req.setReason(rs.getString("reason"));

                list.add(req);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // NEW METHOD — required by Main.java
    @Override
    public List<TimeOffRequest> getRequestsByEmployee(int employeeId) {
        List<TimeOffRequest> list = new ArrayList<>();
        String sql = "SELECT id, employee_id, start_date, end_date, status, reason FROM time_off_requests WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

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
            e.printStackTrace();
        }

        return list;
    }

    // NEW METHOD — required by Main.java
    @Override
    public void updateRequestStatus(int requestId, String newStatus) {
        String sql = "UPDATE time_off_requests SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setInt(2, requestId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
