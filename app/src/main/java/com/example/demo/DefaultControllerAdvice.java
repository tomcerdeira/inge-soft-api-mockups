package com.example.demo;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@ControllerAdvice
public class DefaultControllerAdvice{

//    private static final Logger log = (Logger) LoggerFactory.getLogger(DefaultControllerAdvice.class);


    @ExceptionHandler(ConstraintValueException.class)
    protected ResponseEntity<Object> handleDataConstaint(ConstraintValueException ex, HttpServletRequest request) {
//        log.debug("A REST API error occurred during web call [{}:{}].", request.getMethod(), request.getRequestURI() ,ex);
        RestApiError apiError = new RestApiError(HttpStatus.NOT_ACCEPTABLE,
                "ERROR: duplicate key value violates unique constraint",
                ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @ExceptionHandler(InvalidIdException.class)
    protected ResponseEntity<Object> handleDataNotFound(InvalidIdException ex){
        RestApiError apiError = new RestApiError(HttpStatus.NOT_FOUND,"ERROR: invalid id",ex.getMessage());
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }

    @ExceptionHandler(UnavailableDriverException.class)//unavailable
    protected ResponseEntity<Object> handleUnavailableDriver(InvalidIdException ex){
        RestApiError apiError = new RestApiError(HttpStatus.IM_USED,"ERROR: unavailable driver with id",ex.getMessage());
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }

}
