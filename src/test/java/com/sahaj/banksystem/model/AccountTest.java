package com.sahaj.banksystem.model;

import com.sahaj.banksystem.exceptions.InsufficientBalanceException;
import com.sahaj.banksystem.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void addAmountToTheBalance() {
        Account account = new Account(1000, "Sahaj", 10000);
        account.deposit(10000);
        assertEquals(account.balance(), 20000);
    }

    @Test
    public void subtractAmountToTheBalance() throws InsufficientBalanceException {
        Account account = new Account(1001, "Sahaj", 20000);
        account.withdraw(10000);
        assertEquals(account.balance(), 10000);
    }

    @Test
    public void toGetBalanceAmount() {
        Account account = new Account(1002, "Sahaj", 1000);
        assertEquals(account.balance(), 1000);
    }
}