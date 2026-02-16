package com.example.Spring.LMS.Exceptions;


public class NoPermissionException extends RuntimeException {
    public NoPermissionException(String message) {
        super(message);
    }
}
