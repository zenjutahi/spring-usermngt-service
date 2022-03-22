package com.springboot.userservice.usermanagementservice.exceptions;

import java.util.Date;

// Simple custom error details bean
public class CustomErrorInfo {
    private Date timestamp;
    private String message;
    private String errorDetails;

    // Field Constructors
    public CustomErrorInfo(Date timestamp, String message, String errorDetails) {
        this.timestamp = timestamp;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    // Getters
    public Date getTimestamp() {
        return timestamp;
    }
    public String getMessage() {
        return message;
    }
    public String getErrorDetails() {
        return errorDetails;
    }
}
