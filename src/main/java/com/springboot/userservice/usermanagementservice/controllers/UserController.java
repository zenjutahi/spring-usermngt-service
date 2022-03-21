package com.springboot.userservice.usermanagementservice.controllers;


import com.springboot.userservice.usermanagementservice.entities.User;
import com.springboot.userservice.usermanagementservice.exceptions.UserExistsException;
import com.springboot.userservice.usermanagementservice.exceptions.UserNotFoundException;
import com.springboot.userservice.usermanagementservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }


    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        try {
            return userService.createUser(user);
        } catch (UserExistsException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id){
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }
    // updateUserById
    @PutMapping("/users/{id}")
    public  User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        try {
            return userService.updateUserById(id, user);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }
    // Get user by Username
    @GetMapping("/users/byusername/{username}")
    public User getUserByUserName(@PathVariable("username") String username){
        return userService.getUserByUserName(username);
    }

    // deleteUserById
    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

}
