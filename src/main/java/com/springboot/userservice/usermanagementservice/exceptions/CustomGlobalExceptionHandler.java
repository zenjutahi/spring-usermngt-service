package com.springboot.userservice.usermanagementservice.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Set;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(),
                "From MethodArgumentNotValid Exception in GEH - Argument not Valid", ex.getMessage());
        return new ResponseEntity<Object>(customErrorInfo, HttpStatus.BAD_REQUEST);
    }

    // HttpRequestMethodNotSupportedException
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(),
                "HttpRequestMethodNotSupportedException Exception in GEH - Method not allowed", ex.getMessage());

        return new ResponseEntity<Object>(customErrorInfo, HttpStatus.METHOD_NOT_ALLOWED);
    }

    // UserNameNotFoundException
    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex,
                                                                        WebRequest request){
        CustomErrorInfo customErrorInfo = new CustomErrorInfo(new Date(), ex.getMessage(),
                            request.getDescription(false));

        return new ResponseEntity<Object>(customErrorInfo, HttpStatus.NOT_FOUND);
    }
}
