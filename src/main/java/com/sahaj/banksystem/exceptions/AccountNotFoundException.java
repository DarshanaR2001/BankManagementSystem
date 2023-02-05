package com.sahaj.banksystem.exceptions;

public class AccountNotFoundException extends RuntimeException{
    private final String message;
    public AccountNotFoundException(String message) {
        super(message);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
