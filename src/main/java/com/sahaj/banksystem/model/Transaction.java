package com.sahaj.banksystem.model;

import java.time.LocalDate;

public class Transaction {
    public TransactionTypes type; // DEPOSIT, WITHDRAW, TRANSFER
    public Integer accountNumber;
    public LocalDate date;

    public Transaction(TransactionTypes type, int accountNumber, LocalDate date) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.date = date;
    }

    public TransactionTypes getType() {
        return type;
    }

    public void setType(TransactionTypes type) {
        this.type = type;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
