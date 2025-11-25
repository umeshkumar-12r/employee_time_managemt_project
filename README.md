# Employee Time Management System

## Overview
A Core Java-based system that allows employees to:
- Log work hours
- Request time off
- View their work schedules

## Technologies
- Java
- JDBC
- MySQL
- DAO Pattern
- OOP Concepts

## Setup
1. Create database using `database/employee_time_management.sql`.
2. Update DB credentials in `DBConnection.java`.
3. Add MySQL JDBC driver (mysql-connector) to classpath (`lib/` or IDE).
4. Run `Main.java`.

## Features Implemented
- Database schema design
- JDBC connectivity
- DAO pattern for Employee, WorkEntry, TimeOffRequest
- Basic console-based testing of:
  - Adding employees
  - Logging work hours
  - Requesting time off
  - Viewing work schedule and requests
