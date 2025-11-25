package com.yourname.timemgmt;

import com.yourname.timemgmt.dao.EmployeeDAO;
import com.yourname.timemgmt.dao.TimeOffRequestDAO;
import com.yourname.timemgmt.dao.WorkEntryDAO;
import com.yourname.timemgmt.dao.impl.EmployeeDAOImpl;
import com.yourname.timemgmt.dao.impl.TimeOffRequestDAOImpl;
import com.yourname.timemgmt.dao.impl.WorkEntryDAOImpl;
import com.yourname.timemgmt.model.Employee;
import com.yourname.timemgmt.model.TimeOffRequest;
import com.yourname.timemgmt.model.WorkEntry;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        WorkEntryDAO workEntryDAO = new WorkEntryDAOImpl();
        TimeOffRequestDAO timeOffRequestDAO = new TimeOffRequestDAOImpl();

        // 1. Add an employee
        Employee emp = new Employee("Umesh", "umesh@example.com");
        int empId = employeeDAO.addEmployee(emp);
        System.out.println("Created Employee: " + emp);

        // 2. Log work hours
        WorkEntry entry = new WorkEntry(empId, LocalDate.now(), 8.0,
                "Worked on Employee Time Management System");
        workEntryDAO.addWorkEntry(entry);
        System.out.println("Added Work Entry: " + entry);

        // 3. View employee work entries
        List<WorkEntry> entries = workEntryDAO.getWorkEntriesByEmployee(empId);
        System.out.println("\nWork entries for employee " + empId + ":");
        entries.forEach(System.out::println);

        // 4. Request time off
        TimeOffRequest request = new TimeOffRequest(
                empId,
                LocalDate.now().plusDays(3),
                LocalDate.now().plusDays(5),
                "Family function",
                "PENDING"
        );
        timeOffRequestDAO.addTimeOffRequest(request);
        System.out.println("\nAdded Time Off Request: " + request);

        // 5. View time off requests
        List<TimeOffRequest> requests = timeOffRequestDAO.getRequestsByEmployee(empId);
        System.out.println("\nTime off requests for employee " + empId + ":");
        requests.forEach(System.out::println);

        // 6. Update status of time off request (simulate manager approval)
        if (!requests.isEmpty()) {
            int requestId = requests.get(0).getId();
            timeOffRequestDAO.updateRequestStatus(requestId, "APPROVED");
            System.out.println("\nUpdated Time Off Request ID " + requestId + " to APPROVED");
        }
    }
}
