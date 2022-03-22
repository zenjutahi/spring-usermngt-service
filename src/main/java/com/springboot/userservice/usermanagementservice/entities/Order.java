package com.springboot.userservice.usermanagementservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "orders")
public class Order extends RepresentationModel<Order> {

    @Id
    @GeneratedValue
    private Long id;
    private String orderDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Order() {
    }

    public Order(Long id, String orderDescription, User user) {
        this.id = id;
        this.orderDescription = orderDescription;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
