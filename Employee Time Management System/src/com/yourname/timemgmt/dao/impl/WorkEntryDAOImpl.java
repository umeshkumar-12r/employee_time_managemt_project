package com.yourname.timemgmt.dao.impl;

import com.yourname.timemgmt.dao.WorkEntryDAO;
import com.yourname.timemgmt.model.WorkEntry;
import com.yourname.timemgmt.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkEntryDAOImpl implements WorkEntryDAO {

    @Override
    public void addWorkEntry(WorkEntry workEntry) {
        String sql = "INSERT INTO work_entries (employee_id, hours_worked, work_date, description) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, workEntry.getEmployeeId());
            ps.setDouble(2, workEntry.getHoursWorked());
            ps.setDate(3, Date.valueOf(workEntry.getWorkDate()));
            ps.setString(4, workEntry.getDescription());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<WorkEntry> getWorkEntriesByEmployee(int empId) {
        List<WorkEntry> list = new ArrayList<>();
        String sql = "SELECT id, employee_id, hours_worked, work_date, description FROM work_entries WHERE employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                WorkEntry entry = new WorkEntry();
                entry.setId(rs.getInt("id"));
                entry.setEmployeeId(rs.getInt("employee_id"));
                entry.setHoursWorked(rs.getDouble("hours_worked"));
                entry.setWorkDate(rs.getDate("work_date").toLocalDate());
                entry.setDescription(rs.getString("description"));

                list.add(entry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<WorkEntry> getAllWorkEntries() {
        List<WorkEntry> list = new ArrayList<>();
        String sql = "SELECT id, employee_id, hours_worked, work_date, description FROM work_entries";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                WorkEntry entry = new WorkEntry();
                entry.setId(rs.getInt("id"));
                entry.setEmployeeId(rs.getInt("employee_id"));
                entry.setHoursWorked(rs.getDouble("hours_worked"));
                entry.setWorkDate(rs.getDate("work_date").toLocalDate());
                entry.setDescription(rs.getString("description"));

                list.add(entry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
