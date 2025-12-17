package com.yourname.timemgmt.ui;

import com.yourname.timemgmt.dao.EmployeeDAO;
import com.yourname.timemgmt.dao.TimeOffRequestDAO;
import com.yourname.timemgmt.dao.WorkEntryDAO;
import com.yourname.timemgmt.dao.impl.EmployeeDAOImpl;
import com.yourname.timemgmt.dao.impl.TimeOffRequestDAOImpl;
import com.yourname.timemgmt.dao.impl.WorkEntryDAOImpl;
import com.yourname.timemgmt.model.Employee;
import com.yourname.timemgmt.model.TimeOffRequest;
import com.yourname.timemgmt.model.WorkEntry;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EmployeeTimeManagementUI extends JFrame {

    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private final WorkEntryDAO workEntryDAO = new WorkEntryDAOImpl();
    private final TimeOffRequestDAO timeOffRequestDAO = new TimeOffRequestDAOImpl();

    // Add Employee fields
    private JTextField txtEmpName;
    private JTextField txtEmpEmail;

    // Work entry fields
    private JTextField txtEmpIdWork;
    private JTextField txtHoursWork;
    private JTextField txtDateWork;

    // Time-off fields
    private JTextField txtEmpIdLeave;
    private JTextField txtStartDateLeave;
    private JTextField txtEndDateLeave;
    private JTextArea txtReasonLeave;

    public EmployeeTimeManagementUI() {
        setTitle("Employee Time Management System");
        setSize(650, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Add Employee", createAddEmployeePanel());
        tabbedPane.addTab("Work Entry", createWorkEntryPanel());
        tabbedPane.addTab("Time Off Request", createTimeOffPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    // ------------------------------------------------------
    //                  ADD EMPLOYEE TAB
    // ------------------------------------------------------

    private JPanel createAddEmployeePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblName = new JLabel("Employee Name:");
        JLabel lblEmail = new JLabel("Employee Email:");

        txtEmpName = new JTextField(20);
        txtEmpEmail = new JTextField(20);

        JButton btnAdd = new JButton("Add Employee");
        btnAdd.addActionListener(e -> onAddEmployee());

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblName, gbc);
        gbc.gridx = 1;
        panel.add(txtEmpName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblEmail, gbc);
        gbc.gridx = 1;
        panel.add(txtEmpEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(btnAdd, gbc);

        return panel;
    }

    private void onAddEmployee() {
        String name = txtEmpName.getText().trim();
        String email = txtEmpEmail.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            showError("Name and Email are required.");
            return;
        }

        if (!email.contains("@")) {
            showError("Enter a valid email address.");
            return;
        }

        Employee employee = new Employee(name, email);
        int empId = employeeDAO.addEmployee(employee);

        if (empId > 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Employee added successfully!\nEmployee ID: " + empId
            );
            txtEmpName.setText("");
            txtEmpEmail.setText("");
        } else {
            showError("Failed to add employee. Email may already exist.");
        }
    }

    // ------------------------------------------------------
    //                    WORK ENTRY TAB
    // ------------------------------------------------------

    private JPanel createWorkEntryPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblEmpId = new JLabel("Employee ID:");
        JLabel lblHours = new JLabel("Hours Worked:");
        JLabel lblDate = new JLabel("Work Date (yyyy-MM-dd):");

        txtEmpIdWork = new JTextField(15);
        txtHoursWork = new JTextField(15);
        txtDateWork = new JTextField(15);

        JButton btnSave = new JButton("Save Work Entry");
        btnSave.addActionListener(e -> onSaveWorkEntry());

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblEmpId, gbc);
        gbc.gridx = 1;
        panel.add(txtEmpIdWork, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblHours, gbc);
        gbc.gridx = 1;
        panel.add(txtHoursWork, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblDate, gbc);
        gbc.gridx = 1;
        panel.add(txtDateWork, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(btnSave, gbc);

        return panel;
    }

    private void onSaveWorkEntry() {
        try {
            int employeeId = Integer.parseInt(txtEmpIdWork.getText().trim());
            double hours = Double.parseDouble(txtHoursWork.getText().trim());
            LocalDate date = LocalDate.parse(txtDateWork.getText().trim());

            if (employeeDAO.getEmployeeById(employeeId) == null) {
                showError("Employee not found.");
                return;
            }

            WorkEntry entry = new WorkEntry();
            entry.setEmployeeId(employeeId);
            entry.setHoursWorked(hours);
            entry.setWorkDate(date);
            entry.setDescription("Work logged via UI");

            workEntryDAO.addWorkEntry(entry);
            JOptionPane.showMessageDialog(this, "Work entry saved!");

            txtEmpIdWork.setText("");
            txtHoursWork.setText("");
            txtDateWork.setText("");

        } catch (Exception e) {
            showError("Invalid input. Check values and date format.");
        }
    }

    // ------------------------------------------------------
    //               TIME OFF REQUEST TAB
    // ------------------------------------------------------

    private JPanel createTimeOffPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblEmpId = new JLabel("Employee ID:");
        JLabel lblStart = new JLabel("Start Date (yyyy-MM-dd):");
        JLabel lblEnd = new JLabel("End Date (yyyy-MM-dd):");
        JLabel lblReason = new JLabel("Reason:");

        txtEmpIdLeave = new JTextField(15);
        txtStartDateLeave = new JTextField(15);
        txtEndDateLeave = new JTextField(15);
        txtReasonLeave = new JTextArea(4, 15);

        JScrollPane scroll = new JScrollPane(txtReasonLeave);

        JButton btnSave = new JButton("Submit Time Off Request");
        btnSave.addActionListener(e -> onSaveTimeOffRequest());

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblEmpId, gbc);
        gbc.gridx = 1;
        panel.add(txtEmpIdLeave, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblStart, gbc);
        gbc.gridx = 1;
        panel.add(txtStartDateLeave, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblEnd, gbc);
        gbc.gridx = 1;
        panel.add(txtEndDateLeave, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(lblReason, gbc);
        gbc.gridx = 1;
        panel.add(scroll, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(btnSave, gbc);

        return panel;
    }

    private void onSaveTimeOffRequest() {
        try {
            int employeeId = Integer.parseInt(txtEmpIdLeave.getText().trim());
            LocalDate start = LocalDate.parse(txtStartDateLeave.getText().trim());
            LocalDate end = LocalDate.parse(txtEndDateLeave.getText().trim());

            if (employeeDAO.getEmployeeById(employeeId) == null) {
                showError("Employee not found.");
                return;
            }

            TimeOffRequest req = new TimeOffRequest();
            req.setEmployeeId(employeeId);
            req.setStartDate(start);
            req.setEndDate(end);
            req.setReason(txtReasonLeave.getText().trim());
            req.setStatus("PENDING");

            timeOffRequestDAO.addTimeOffRequest(req);
            JOptionPane.showMessageDialog(this, "Time off request submitted!");

            txtEmpIdLeave.setText("");
            txtStartDateLeave.setText("");
            txtEndDateLeave.setText("");
            txtReasonLeave.setText("");

        } catch (Exception e) {
            showError("Invalid input or date format.");
        }
    }

    // ------------------------------------------------------
    //                      COMMON
    // ------------------------------------------------------

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new EmployeeTimeManagementUI().setVisible(true)
        );
    }
}
