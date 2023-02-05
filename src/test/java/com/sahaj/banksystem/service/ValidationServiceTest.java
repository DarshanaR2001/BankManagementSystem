package com.sahaj.banksystem.service;

import com.sahaj.banksystem.exceptions.AmountLimitException;
import com.sahaj.banksystem.exceptions.InvalidTransactionCountException;
import com.sahaj.banksystem.model.Transaction;
import com.sahaj.banksystem.model.TransactionTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    private static final int DEPOSIT_MINIMUM_AMOUNT = 500;
    private static final int DEPOSIT_MAXIMUM_AMOUNT = 25000;
    private static final int WITHDRAWAL_MINIMUM_AMOUNT = 1000;
    private static final int WITHDRAWAL_MAXIMUM_AMOUNT = 50000;
    private ValidationService validationService;

    @BeforeEach
    public void setUp() {
        validationService = new ValidationService();
    }

    @DisplayName("To Check if the amount is less than minimum deposit amount")
    @ParameterizedTest
    @ValueSource(ints = {200, 400})
    public void checkDepositAmountLessThanMinimumDepositAmount(Integer amount) {
        Exception exception = assertThrows(AmountLimitException.class, () -> {validationService.checkLimit(amount, DEPOSIT_MINIMUM_AMOUNT, DEPOSIT_MAXIMUM_AMOUNT, String.valueOf(TransactionTypes.DEPOSIT));});
        assertEquals("Minimum deposit amount is 500", exception.getMessage());
    }

    @DisplayName("To Check if the amount is greater than maximum deposit amount")
    @ParameterizedTest
    @ValueSource(ints = {26000, 40000})
    public void checkDepositAmountGreaterThanMinimumDepositAmount(Integer amount) {
        Exception exception = assertThrows(AmountLimitException.class, () -> {validationService.checkLimit(amount, DEPOSIT_MINIMUM_AMOUNT, DEPOSIT_MAXIMUM_AMOUNT, String.valueOf(TransactionTypes.DEPOSIT));});
        assertEquals("Maximum deposit amount is 25000", exception.getMessage().replace(",", ""));
    }

    @DisplayName("To Check if the amount is less than maximum withdrawal amount")
    @ParameterizedTest
    @ValueSource(ints = {900, 500})
    public void checkWithdrawalAmountLessThanMinimumWithdrawalAmount(Integer amount) {
        Exception exception = assertThrows(AmountLimitException.class, () -> {validationService.checkLimit(amount, WITHDRAWAL_MINIMUM_AMOUNT, WITHDRAWAL_MAXIMUM_AMOUNT, String.valueOf(TransactionTypes.WITHDRAW));});
        assertEquals("Minimum withdraw amount is 1000", exception.getMessage().replace(",", ""));
    }

    @DisplayName("To Check if the amount is greater than maximum withdrawal amount")
    @ParameterizedTest
    @ValueSource(ints = {60000, 90000})
    public void checkWithdrawalAmountGreaterThanMinimumWithdrawalAmount(Integer amount) {
        Exception exception = assertThrows(AmountLimitException.class, () -> {validationService.checkLimit(amount, WITHDRAWAL_MINIMUM_AMOUNT, WITHDRAWAL_MAXIMUM_AMOUNT, String.valueOf(TransactionTypes.WITHDRAW));});
        assertEquals("Maximum withdraw amount is 50000", exception.getMessage().replace(",", ""));
    }

    @DisplayName("To get the count of transactions per day")
    @ParameterizedTest
    @ValueSource(strings = {"DEPOSIT", "WITHDRAW"})
    public void transactionsPerDay(TransactionTypes transactionType) {
        List<Transaction> transactionList = new ArrayList<>();
        assertEquals(0, validationService.getTransactionCount(transactionList, transactionType));
    }

    @DisplayName("To Check if the deposit transaction count is more than the limit")
    @Test
    public void depositTransactionCountMoreThanLimit() {
        Exception exception = assertThrows(InvalidTransactionCountException.class, () -> {validationService.checkTransactionCount(4, TransactionTypes.DEPOSIT);});
        assertEquals("Only 3 deposit allowed in a day", exception.getMessage());
    }

    @DisplayName("To Check if the withdrawal transaction count is more than the limit")
    @Test
    public void withdrawTransactionCountMoreThanLimit() {
        Exception exception = assertThrows(InvalidTransactionCountException.class, () -> {validationService.checkTransactionCount(5, TransactionTypes.WITHDRAW);});
        assertEquals("Only 3 withdraw allowed in a day", exception.getMessage());
    }
}