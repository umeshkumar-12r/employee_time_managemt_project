-- Create the database if it does not already exist
CREATE DATABASE IF NOT EXISTS employee_time_management;

-- Switch to using the employee_time_management database
USE employee_time_management;

-- Create the employees table if it does not exist
CREATE TABLE IF NOT EXISTS employees (
    -- Unique identifier for each employee, auto-incremented
                                         id INT AUTO_INCREMENT PRIMARY KEY,
    -- Employee's full name, required and max 100 characters
                                         name VARCHAR(100) NOT NULL,
    -- Employee's unique email address, required
    email VARCHAR(100) UNIQUE NOT NULL
    );

-- Create the work_entries table if it does not exist
CREATE TABLE IF NOT EXISTS work_entries (
    -- Unique identifier for each work entry, auto-incremented
                                            id INT AUTO_INCREMENT PRIMARY KEY,
    -- Foreign key referencing employee's ID, cannot be null
                                            employee_id INT NOT NULL,
    -- The date of the work entry, required
                                            work_date DATE NOT NULL,
    -- Number of hours worked, precision 4 digits, 2 after decimal, required
                                            hours_worked DECIMAL(4,2) NOT NULL,
    -- Optional textual description of the work entry
    description VARCHAR(255),
    -- Foreign key constraint linking employee_id to employees table
    FOREIGN KEY (employee_id) REFERENCES employees(id)
    );

-- Create the time_off_requests table if it does not exist
CREATE TABLE IF NOT EXISTS time_off_requests (
    -- Unique identifier for each time off request, auto-incremented
                                                 id INT AUTO_INCREMENT PRIMARY KEY,
    -- Foreign key referencing employee's ID, cannot be null
                                                 employee_id INT NOT NULL,
    -- Start date of requested time off, required
                                                 start_date DATE NOT NULL,
    -- End date of requested time off, required
                                                 end_date DATE NOT NULL,
    -- Reason provided for the time off request
                                                 reason VARCHAR(255),
    -- Status of the request with limited allowable values, defaults to 'PENDING'
    status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
    -- Foreign key constraint linking employee_id to employees table
    FOREIGN KEY (employee_id) REFERENCES employees(id)
    );
