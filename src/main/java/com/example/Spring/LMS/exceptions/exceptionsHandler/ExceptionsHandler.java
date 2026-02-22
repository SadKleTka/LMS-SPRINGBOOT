package com.example.Spring.LMS.exceptions.exceptionsHandler;

import com.example.Spring.LMS.exceptions.ErrorMessages;
import com.example.Spring.LMS.exceptions.NoPermissionException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    private static final Logger log = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity handleSecurityException(
            SecurityException e
    ) {
        log.error("Security Exception", e);

        var error = new ErrorMessages(
                "Password do not match requirements",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGlobalException(
            Exception e
    ) {
        log.error("Global Exception: ", e);

        var error = new ErrorMessages(
                "Server Error",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(
            EntityNotFoundException e
    ) {
        log.error("Entity Not Found: ", e);

        var error = new ErrorMessages(
                "Entity not found",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(exception = {
            IllegalStateException.class,
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity handleBadRequestException(
            Exception e
    ) {
        log.error("Bad Request: ", e);

        var error = new ErrorMessages(
                "Bad request",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(NoPermissionException.class)
    public ResponseEntity handleNoPermissionException(
            NoPermissionException e
    ) {
        log.error("No Permission: ", e);

        var error = new ErrorMessages(
                "You have no permission to do this",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(error);
    }
}
