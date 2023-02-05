package com.sahaj.banksystem.service;

import com.sahaj.banksystem.exceptions.AccountNotFoundException;
import com.sahaj.banksystem.exceptions.InsufficientBalanceException;
import com.sahaj.banksystem.model.Account;
import com.sahaj.banksystem.model.Transaction;
import com.sahaj.banksystem.model.TransactionTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountService {
    private static final int DEPOSIT_MINIMUM_AMOUNT = 500;
    private static final int DEPOSIT_MAXIMUM_AMOUNT = 50000;
    private static final int WITHDRAW_MINIMUM_AMOUNT = 1000;
    private static final int WITHDRAW_MAXIMUM_AMOUNT = 25000;
    static Integer lastAccountNumber = 1000;
    HashMap<Integer, Account> accounts = new HashMap<>();
    HashMap<Integer, List<Transaction>> transaction = new HashMap<>();
    ValidationService validationService = new ValidationService();

    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }

    public Integer createAccount(String accountName) {
        Integer accountBalance = 0;
        Account account = new Account(lastAccountNumber, accountName, accountBalance);
        accounts.put(lastAccountNumber, account);
        System.out.println(lastAccountNumber);
        lastAccountNumber = lastAccountNumber + 1;
        return account.getNumber();
    }

    public void deposit(Integer accountNumber, Integer amount) {
        if (accounts.containsKey(accountNumber)) {
            List<Transaction> transactions = this.transaction.getOrDefault(accountNumber,new ArrayList<>());
            validationService.checkLimit(amount, DEPOSIT_MINIMUM_AMOUNT, DEPOSIT_MAXIMUM_AMOUNT, String.valueOf(TransactionTypes.DEPOSIT));
            Integer depositCount = validationService.getTransactionCount(transactions, TransactionTypes.DEPOSIT);
            validationService.checkTransactionCount(depositCount, TransactionTypes.DEPOSIT);
            Account account = accounts.get(accountNumber);
            System.out.println(account.deposit(amount));
            transactions.add(new Transaction(TransactionTypes.DEPOSIT, accountNumber, LocalDate.now()));
        } else {
            throw new AccountNotFoundException("Invalid Account number");
        }
    }

    public void withdraw(Integer accountNumber, Integer amount) {
        if (accounts.containsKey(accountNumber)) {
            List<Transaction> transactions = this.transaction.getOrDefault(accountNumber,new ArrayList<>());
            validationService.checkLimit(amount, WITHDRAW_MINIMUM_AMOUNT, WITHDRAW_MAXIMUM_AMOUNT, String.valueOf(TransactionTypes.WITHDRAW));
            Integer withdrawalCount = validationService.getTransactionCount(transactions, TransactionTypes.WITHDRAW);
            validationService.checkTransactionCount(withdrawalCount, TransactionTypes.WITHDRAW);
            Account account = accounts.get(accountNumber);
            try {
                account.withdraw(amount);
            } catch (InsufficientBalanceException exception) {
                System.out.println(exception.getMessage());
                throw exception;
            }
            transactions.add(new Transaction(TransactionTypes.WITHDRAW, accountNumber, LocalDate.now()));
        } else {
            throw new AccountNotFoundException("Invalid Account number");
        }
    }

    public void balance(Integer accountNumber) {
        Account account = accounts.get(accountNumber);
        System.out.println(account.balance());
    }

    public void transfer(Integer sourceAccountNumber, Integer targetAccountNumber, int amount) {
        if (accounts.containsKey(sourceAccountNumber)) {
            withdraw(sourceAccountNumber, amount);
        }
        if (accounts.containsKey(targetAccountNumber)) {
            deposit(targetAccountNumber, amount);
            System.out.println("Successful");
        }
    }
}
