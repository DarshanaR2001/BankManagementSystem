package com.sahaj.banksystem.model;

import com.sahaj.banksystem.exceptions.InsufficientBalanceException;

public class Account {
    Integer number;
    String name;
    Integer balance = 0;

    public Account(Integer number, String name, Integer balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    public Integer deposit(int amount) {
        balance += amount;
        return balance;
    }

    public void withdraw(int amount) throws InsufficientBalanceException {
        if(balance < amount) {
            throw new InsufficientBalanceException(String.format("Insufficient Balance"));
        }
        balance -= amount;
        System.out.println(balance);
    }

    public Integer balance() {
        return balance;
    }

    public Integer getNumber() {
        return number;
    }
}
