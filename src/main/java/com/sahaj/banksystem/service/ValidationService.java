package com.sahaj.banksystem.service;

import com.sahaj.banksystem.exceptions.AmountLimitException;
import com.sahaj.banksystem.exceptions.InvalidTransactionCountException;
import com.sahaj.banksystem.model.Transaction;
import com.sahaj.banksystem.model.TransactionTypes;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;

public class ValidationService {
    private static final Integer maximumCountPerDay = 3;

    public void checkLimit(Integer amount, Integer minimumAmount, Integer maximumAmount, String transactionType) {
        try {
            if (amount < minimumAmount) {
                throw new AmountLimitException(MessageFormat.format("Minimum {0} amount is {1}", transactionType.toLowerCase(), minimumAmount));
            } else if (amount > maximumAmount) {
                throw new AmountLimitException(MessageFormat.format("Maximum {0} amount is {1}", transactionType.toLowerCase(), maximumAmount));
            }
        } catch (AmountLimitException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }

    public Integer getTransactionCount(List<Transaction> transactions, TransactionTypes transactionType) {
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(transactionType)) {
                LocalDate date = transaction.getDate();
                if (date.isEqual(LocalDate.now())) {
                    count++;
                }
            }
        }
        return count;
    }

    public void checkTransactionCount(Integer count, TransactionTypes transactionType) {
        try {
            if (count > maximumCountPerDay) {
                throw new InvalidTransactionCountException(MessageFormat.format("Only 3 {0} allowed in a day", transactionType.toString().toLowerCase()));
            }
        } catch (InvalidTransactionCountException exception) {
            System.out.println(exception.getMessage());
            throw exception;
        }
    }
}
