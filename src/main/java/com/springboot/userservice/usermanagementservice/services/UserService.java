package com.springboot.userservice.usermanagementservice.services;


import com.springboot.userservice.usermanagementservice.entities.User;
import com.springboot.userservice.usermanagementservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // get all users
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    // Create user method
    public User createUser(User user){
        return userRepository.save(user);
    }

    // Get userById method
    public Optional<User> getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user;
    }


}
