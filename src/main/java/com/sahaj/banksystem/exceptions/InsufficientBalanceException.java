package com.sahaj.banksystem.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    private final String message;
    public InsufficientBalanceException(String message) {
        super(message);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
