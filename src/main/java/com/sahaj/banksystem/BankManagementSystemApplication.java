package com.sahaj.banksystem;

import com.sahaj.banksystem.service.AccountService;
import java.util.Scanner;

public class BankManagementSystemApplication {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            switch (parts[0]) {
                case "Create":
                    accountService.createAccount(parts[1]);
                    break;
                case "Deposit":
                    accountService.deposit(Integer.valueOf(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "Withdraw":
                    accountService.withdraw(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case "Balance":
                    accountService.balance(Integer.parseInt(parts[1]));
                    break;
                case "Transfer":
                    accountService.transfer(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                    break;
            }
        }
    }
}
