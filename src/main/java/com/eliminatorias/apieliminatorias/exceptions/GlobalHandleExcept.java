package com.eliminatorias.apieliminatorias.exceptions;

import com.eliminatorias.apieliminatorias.exceptions.matchExcept.MatchNoFoundExceptByDate;
import com.eliminatorias.apieliminatorias.exceptions.teamExcept.TeamNoFoundExcep;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
