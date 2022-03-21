package com.springboot.userservice.usermanagementservice.exceptions;


public class UserExistsException extends Exception {

    private static final long serialVersionUid = 1L;

    public UserExistsException(String message) {
        super(message);
    }
}
