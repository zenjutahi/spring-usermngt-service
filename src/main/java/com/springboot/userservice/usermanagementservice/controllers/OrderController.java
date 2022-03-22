package com.springboot.userservice.usermanagementservice.controllers;

import com.springboot.userservice.usermanagementservice.entities.Order;
import com.springboot.userservice.usermanagementservice.entities.User;
import com.springboot.userservice.usermanagementservice.exceptions.UserNotFoundException;
import com.springboot.userservice.usermanagementservice.repositories.OrderRepository;
import com.springboot.userservice.usermanagementservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    // GET all orders for a user
    @GetMapping("/{user_id}/orders")
    public List<Order> getAllOrders(@PathVariable Long user_id) throws UserNotFoundException {

        Optional<User> userOptional = userRepository.findById(user_id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User Not Found");
        }
        return userOptional.get().getOrders();
    }

    // Create Order for a user
    @PostMapping("/{user_id}/orders")
    public Order createOrder(@PathVariable Long user_id, @RequestBody Order order) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(user_id);

        if(!userOptional.isPresent()){
            throw new UserNotFoundException("User Not Found");
        }
        User user = userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);
    }
}
