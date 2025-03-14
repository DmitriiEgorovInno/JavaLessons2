package org.example.controller;

import org.example.exception.PaymentException;
import org.example.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentsExceptionHandler {
    @ExceptionHandler(PaymentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlePaymentException(PaymentException paymentException){
        return new ErrorResponse(paymentException.getMessage(),paymentException.getExternlSystemCode());
    }
}
