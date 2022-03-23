package com.springboot.userservice.usermanagementservice.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

// Entity maps to the row in the database
// @Table defines the table name
//@JsonIgnoreProperties({"role", "lastName"} ) -- static filtering @JsonIgnore
@Entity
@Table(name = "user")
//@JsonFilter(value = "userFilter") -- for MappingJacksonValue filtering section
public class User extends RepresentationModel<User> {

    @Id
    @GeneratedValue
    @JsonView(Views.External.class)
    private Long id;

    //set name , length and unique for column using @Column
    @NotEmpty(message = "Username is Mandatory field. Please provide username")
    @Column(name="USER_NAME", length = 50, nullable = false,unique = true)
    @JsonView(Views.External.class)
    private String userName;

    @Size(min=2, message="FirstName should have atleast 2 characters")
    @Column(name="FIRST_NAME", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String firstName;

    @Column(name="LAST_NAME", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String lastName;

    @Column(name="EMAIL_ADDRESS", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String email;

    @Column(name="ROLE", length = 50, nullable = false)
    @JsonView(Views.Internal.class)
    private String role;

//    @JsonIgnore -- static filtering @JsonIgnore
    @Column(name="SSN", length = 50, nullable = false, unique = true)
    @JsonView(Views.Internal.class)
    private String ssn;

    @OneToMany(mappedBy = "user")
    @JsonView(Views.Internal.class)
    private List<Order> orders;

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

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

    public List<Order> getOrders() {
        return orders;
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
