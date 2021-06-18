package org.codejudge.sb.services;

import org.codejudge.sb.model.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TAYYAB
 */
@ControllerAdvice
public class DriverExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response> handleValidationExceptions(ConstraintViolationException exception) {
        List<ConstraintViolation> allErrors = new ArrayList<>(exception.getConstraintViolations());
        if (allErrors.stream().findFirst().isPresent()) {
            allErrors.stream().findFirst().get();
            String error = allErrors.stream().findFirst().get().getMessage();
            Response response = new Response("failure", error, "");
            return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Response>(new Response("failure", "error", ""), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response> handleValidationExceptions(DataIntegrityViolationException exception) {
        Response response = new Response("failure", "DataIntegrityViolationException", "");
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleValidationExceptions(Exception exception) {
        Response response = new Response("failure", exception.getMessage(),"");
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }
}
