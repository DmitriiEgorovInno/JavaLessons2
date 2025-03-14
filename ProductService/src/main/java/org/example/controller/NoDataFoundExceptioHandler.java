package org.example.controller;

import org.example.dto.ErrorResponse;
import org.example.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NoDataFoundExceptioHandler {
    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoDataFoundExceptiob(NoDataFoundException noDataFoundException){
        return new ErrorResponse(noDataFoundException.getMessage(),noDataFoundException.getExternalSystemCode());
    }
}
