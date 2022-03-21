package com.springboot.userservice.usermanagementservice.entities;

import javax.persistence.*;

// Entity maps to the row in the database
// @Table defines the table name
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    //set name , length and unique for column using @Column
    @Column(name="USER_NAME", length = 50, nullable = false,unique = true)
    private String userName;

    @Column(name="FIRST_NAME", length = 50, nullable = false)
    private String firstName;

    @Column(name="LAST_NAME", length = 50, nullable = false)
    private String lastName;

    @Column(name="EMAIL_ADDRESS", length = 50, nullable = false)
    private String email;

    @Column(name="ROLE", length = 50, nullable = false)
    private String role;

    @Column(name="SSN", length = 50, nullable = false, unique = true)
    private String ssn;

    // No Argument constructor
    public User() {
    }

    // Field Constructors
    public User(Long id, String userName, String firstName, String lastName, String email, String role, String ssn) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }

    // Setter and Getters


    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getSsn() {
        return ssn;
    }

    // To string
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}
