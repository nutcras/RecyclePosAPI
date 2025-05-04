package com.example.sellWebApp.exception;

import com.example.sellWebApp.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.EntityNotFoundException.class)
    public final ResponseEntity<?> handleNotFountExceptions(Exception ex, WebRequest request) {
        Response<?> response = Response.buildNotFound();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.DuplicateEntityException.class)
    public final ResponseEntity<?> handleNotFountExceptions1(Exception ex, WebRequest request) {
        Response<?> response = Response.duplicateEntity();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BusinessException.LoginAttemptException.class)
    public final ResponseEntity<?> handleLoginAttemptException(Exception ex, WebRequest request) {
        Response<?> response = Response.exception();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<?> handleAuthenticationException(Exception ex, WebRequest request) {
        Response<?> response = Response.exception();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleExceptions(Exception ex, WebRequest request) {

        Response<?> response = Response.exception();
        response.addErrorMsgToResponse("", ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public final ResponseEntity<?> handleExceptions(BadSqlGrammarException ex, WebRequest request) {

        Response<?> response = Response.exception();
        response.addErrorMsgToResponse("", new Exception("SQL Syntax Error Exception"));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
