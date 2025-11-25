package com.yourname.timemgmt.dao;

import com.yourname.timemgmt.model.Employee;
import java.util.List;

/**
 * Data Access Object interface for CRUD operations on Employee entities.
 * Defines methods to add employees and fetch employee data.
 */
public interface EmployeeDAO {

    /**
     * Adds a new employee record to the data store.
     *
     * @param employee the Employee object to add
     * @return the ID of the newly inserted employee, or a negative value if adding fails
     */
    int addEmployee(Employee employee);

    /**
     * Retrieves the Employee record with the specified ID.
     *
     * @param id the ID of the employee to retrieve
     * @return the Employee object matching the ID, or null if not found
     */
    Employee getEmployeeById(int id);

    /**
     * Retrieves a list of all employees in the data store.
     *
     * @return a List of all Employee objects (empty list if none found)
     */
    List<Employee> getAllEmployees();
}
