package com.example.Spring.LMS.exceptions;


public class NoPermissionException extends RuntimeException {
    public NoPermissionException(String message) {
        super(message);
    }
}
