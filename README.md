Employee Time Management System
📌 Overview

The Employee Time Management System is a Core Java–based application designed to manage employee work activities.
It enables employees to log work hours, submit time-off requests, and view work-related records using a structured, database-driven approach.

The project demonstrates core Java concepts, JDBC connectivity, and DAO design pattern, making it suitable for academic evaluation and learning purposes.

🚀 Features

Add and manage employees

Log daily work hours

Submit time-off requests

View work entries and leave requests

Input validation at UI and service level

Modular design using DAO pattern

🛠️ Technologies Used

Java (Core Java, OOP)

JDBC

MySQL

Swing (GUI)

DAO Design Pattern

🗂️ Project Structure
Employee-Time-Management-System
│
├── dao
│   ├── EmployeeDAO.java
│   ├── WorkEntryDAO.java
│   └── TimeOffRequestDAO.java
│
├── dao/impl
│   ├── EmployeeDAOImpl.java
│   ├── WorkEntryDAOImpl.java
│   └── TimeOffRequestDAOImpl.java
│
├── model
│   ├── Employee.java
│   ├── WorkEntry.java
│   └── TimeOffRequest.java
│
├── ui
│   └── EmployeeTimeManagementUI.java
│
├── util
│   └── DBConnection.java
│
└── database
    └── employee_time_management.sql

🗄️ Database Design

The system uses a MySQL database with the following tables:

employees – Stores employee information

work_entries – Stores logged work hours

time_off_requests – Stores leave requests

Foreign key relationships ensure data integrity between employees and their work/leave records.

⚙️ Setup Instructions

Create Database

Run database/employee_time_management.sql in MySQL


Configure Database Connection

Update username and password in DBConnection.java

Add MySQL JDBC Driver

Add mysql-connector-j JAR to:

IntelliJ → Project Structure → Modules → Dependencies

Run Application

Run EmployeeTimeManagementUI.java

✅ Features Implemented (Review-1 Scope)

Database schema design

JDBC connectivity with MySQL

DAO pattern implementation

Swing-based user interface

Employee creation

Work entry logging

Time-off request submission

⚠️ Known Limitations

Authentication and role-based access are not implemented

Reporting and export features are not included

Some environments may require JDBC driver version alignment

🔮 Future Enhancements

Login system for Admin and Employees

JTable-based data views

Approval workflow for time-off requests

Web-based version using Servlets and JSP

Enhanced validation and exception handling

📚 Learning Outcomes

Hands-on experience with JDBC & MySQL

Practical use of DAO design pattern

Understanding Java Swing UI

Applying OOP principles in real projects
