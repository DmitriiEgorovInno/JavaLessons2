package org.example.exception;

import jakarta.persistence.EntityNotFoundException;

public class NoDataFoundException extends EntityNotFoundException {
    private final String externalSystemCode;

    public NoDataFoundException(String message, String externalSystemCode){
        super(message);
        this.externalSystemCode=externalSystemCode;
    }

    public String getExternalSystemCode() {
        return externalSystemCode;
    }
}
