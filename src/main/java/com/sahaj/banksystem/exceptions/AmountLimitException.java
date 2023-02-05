package com.sahaj.banksystem.exceptions;

public class AmountLimitException extends RuntimeException {
    private final String message;
    public AmountLimitException(String message) {
        super(message);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
