package com.springboot.userservice.usermanagementservice.exceptions;

public class UserNameNotFoundException extends Exception{
    private static final long serialVersionUid = 1L;

    public UserNameNotFoundException(String message) {
        super(message);
    }
}
