package com.springboot.userservice.usermanagementservice.controllers;

import com.springboot.userservice.usermanagementservice.entities.Order;
import com.springboot.userservice.usermanagementservice.entities.User;
import com.springboot.userservice.usermanagementservice.exceptions.UserNotFoundException;
import com.springboot.userservice.usermanagementservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    // GET all orders for a user
    @GetMapping("/{user_id}/orders")
    public List<Order> getAllOrders(@PathVariable Long user_id) throws UserNotFoundException {

        Optional<User> user = userRepository.findById(user_id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User Not Found");
        }
        return user.get().getOrders();
    }
}
