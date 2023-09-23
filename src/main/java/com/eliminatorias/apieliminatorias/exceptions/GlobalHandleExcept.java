package com.eliminatorias.apieliminatorias.exceptions;

import com.eliminatorias.apieliminatorias.exceptions.matchExcept.MatchNoFoundExceptByDate;
import com.eliminatorias.apieliminatorias.exceptions.teamExcept.TeamNoFoundExcep;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalHandleExcept {
    @ExceptionHandler(TeamNoFoundExcep.class)
    public ResponseEntity<ErrorResponse> handleTeamException(TeamNoFoundExcep ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    //match exceptions
    @ExceptionHandler(MatchNoFoundExceptByDate.class)
    public ResponseEntity<ErrorResponse> handleMatchException(MatchNoFoundExceptByDate ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                HttpHeaders httpHeaders, HttpStatus httpStatus,
                                                                                WebRequest request){
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),request.getDescription(false));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handlerConstrainViolationException(Exception ex, WebRequest request){

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }
}
