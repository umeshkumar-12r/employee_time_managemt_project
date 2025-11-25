package com.yourname.timemgmt.model;

/**
 * Represents an employee with an ID, name, and email address.
 */
public class Employee {

    // Unique identifier for the employee.
    private int id;

    // Employee's full name.
    private String name;

    // Employee's email address.
    private String email;

    /**
     * No-argument constructor required for frameworks and serialization.
     */
    public Employee() {}

    /**
     * Constructor to initialize all fields including the ID.
     * @param id unique employee ID
     * @param name employee's full name
     * @param email employee's email address
     */
    public Employee(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Constructor to initialize name and email for new employees before ID assignment.
     * @param name employee's full name
     * @param email employee's email address
     */
    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Standard getters and setters for each field.

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    /**
     * Returns a string representation useful for debugging and logging.
     */
    @Override
    public String toString() {
        return "Employee{id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
