package com.springboot.userservice.usermanagementservice.services;


import com.springboot.userservice.usermanagementservice.entities.User;
import com.springboot.userservice.usermanagementservice.exceptions.UserExistsException;
import com.springboot.userservice.usermanagementservice.exceptions.UserNotFoundException;
import com.springboot.userservice.usermanagementservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public User createUser(User user) throws UserExistsException{
        // check if user exist using username
        User existingUser = userRepository.findByUserName(user.getUserName());
        // throw true throw UserExistsException
        if(existingUser !=null) {
            throw new UserExistsException("Use another username to create User");
        }
        return userRepository.save(user);
    }

    // Get userById method
    public Optional<User> getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException("User Not Found in user Repository");
        }
        return user;
    }

    // update user byId
    public User updateUserById(Long id, User user) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);

        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User Not Found in user Repository, provide correct user Id");
        }
        user.setId(id);
        return userRepository.save(user);
    }
    // Get User By Username
    public User getUserByUserName(String username){
        return  userRepository.findByUserName(username);
    }

    // deleteUserById
    public void deleteUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(!optionalUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "provide correct user Id");
        }
        userRepository.deleteById(id);
    }

}
