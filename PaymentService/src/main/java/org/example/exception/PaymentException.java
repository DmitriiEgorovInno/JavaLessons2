package org.example.exception;

public class PaymentException extends RuntimeException{
    private final String externlSystemCode;

    public PaymentException(String message,String externlSystemCode){
        super(message);
        this.externlSystemCode=externlSystemCode;
    }

    public String getExternlSystemCode() {
        return externlSystemCode;
    }
}
