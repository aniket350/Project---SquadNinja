package com.stackroute.exception;
/**
 * Custom Exception to throw if user already exists
 */
public class UserAlreadyFoundException extends Exception {

    private String message;

    public UserAlreadyFoundException(String message) {
        super(message);
        this.message = message;
    }
}
