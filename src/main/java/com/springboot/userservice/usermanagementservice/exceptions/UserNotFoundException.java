package com.springboot.userservice.usermanagementservice.exceptions;

public class UserNotFoundException extends Exception{

    private static final long serialVersionUid = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
