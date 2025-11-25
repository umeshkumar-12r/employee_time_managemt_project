package com.yourname.timemgmt.dao.impl;

import com.yourname.timemgmt.dao.EmployeeDAO;
import com.yourname.timemgmt.model.Employee;
import com.yourname.timemgmt.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    /**
     * Adds a new Employee record to the 'employees' table.
     * Uses a prepared statement to set parameters and executes the insert.
     * Retrieves and sets the generated primary key on the Employee object.
     *
     * @param employee Employee object to add
     * @return generated ID of the new employee, or -1 if insertion fails
     */
    @Override
    public int addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, email) VALUES (?, ?)";
        // Use try-with-resources to ensure connection and statement are closed properly
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Bind employee name and email to the query parameters
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());

            // Execute update and check if insert affected rows
            int rows = ps.executeUpdate();
            if (rows > 0) {
                // Retrieve generated keys (IDs)
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        employee.setId(id); // Set generated ID in the employee object
                        return id; // Return new employee ID
                    }
                }
            }
        } catch (SQLException e) {
            // Log failure and stacktrace
            System.out.println("Error adding employee");
            e.printStackTrace();
        }
        // Return -1 if insert fails or no ID generated
        return -1;
    }

    /**
     * Retrieves an Employee by their unique ID.
     * Queries the database using a prepared statement and maps the result to an Employee object.
     * Returns null if no matching employee is found.
     *
     * @param id unique employee ID
     * @return Employee object or null if not found
     */
    @Override
    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee emp = new Employee();
                    emp.setId(rs.getInt("id"));
                    emp.setName(rs.getString("name"));
                    emp.setEmail(rs.getString("email"));
                    return emp;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employee by id");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a list of all employees in the 'employees' table.
     * Executes a simple SELECT query and maps each row to an Employee object.
     * Returns an empty list if no employees exist or upon errors.
     *
     * @return list of Employee objects
     */
    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employees";
        List<Employee> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Iterate over all result rows and create Employee objects
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                list.add(emp);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all employees");
            e.printStackTrace();
        }
        return list;
    }
}
