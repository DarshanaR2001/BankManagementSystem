package com.sahaj.banksystem.service;

import com.sahaj.banksystem.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceTest {

    private AccountService accountService;

    @BeforeEach
    public void setup() {
        accountService = new AccountService();
    }

    @DisplayName("To create an Account")
    @Test
    public void toCreateAccount() {
        accountService.createAccount("TestBankAccountName");

        assertEquals(1, accountService.getAccounts().size());
    }

    @DisplayName("To deposit amount in the account")
    @Test
    public void depositTransaction() {
        Integer accountNumber = accountService.createAccount("Sahaj");

        accountService.deposit(accountNumber, 20000);

        assertEquals(20000, accountService.getAccounts().get(accountNumber).balance());
    }

    @DisplayName("To withdraw amount from the account")
    @Test
    public void withdrawTransaction() {
        Integer accountNumber = accountService.createAccount("Sahaj");

        accountService.deposit(accountNumber, 20000);
        accountService.withdraw(accountNumber, 10000);

        assertEquals(10000, accountService.getAccounts().get(accountNumber).balance());
    }

    @DisplayName("Insufficient Balance Exception is thrown if the amount is greater than account balance")
    @Test
    public void exceptionInWithdrawTransaction() {
        Integer accountNumber = accountService.createAccount("Sahaj");
        Exception exception = assertThrows(InsufficientBalanceException.class, () -> {accountService.withdraw(accountNumber, 20000);});
        assertEquals("Insufficient Balance", exception.getMessage());
    }

    @DisplayName("get balance for the given account number")
    @Test
    public void getBalance() {
        Integer accountNumber = accountService.createAccount(Integer.toString(1000));

        accountService.deposit(accountNumber, 20000);
        accountService.balance(accountNumber);

        assertEquals(20000, accountService.getAccounts().get(accountNumber).balance());
    }

    @DisplayName("To transfer amount from source account to target account")
    @Test
    public void toTransferAmount() {
        Integer sourceAccountNumber = accountService.createAccount("TestBankSourceAccountName");
        Integer targetAccountNumber = accountService.createAccount("TestBankTargetAccountName");

        accountService.deposit(sourceAccountNumber, 40000);
        accountService.transfer(sourceAccountNumber, targetAccountNumber, 20000);

        assertEquals(20000, accountService.getAccounts().get(sourceAccountNumber).balance());
        assertEquals(20000, accountService.getAccounts().get(targetAccountNumber).balance());
    }
}