package com.sahaj.banksystem.exceptions;

public class InvalidTransactionCountException extends RuntimeException{
    private final String message;
    public InvalidTransactionCountException(String message) {
        super(message);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
