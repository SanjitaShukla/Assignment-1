package com.aurionpro.model;

public class BankAccountTest {
	public static void main(String[] args) {
        try {
            BankAccount account = new BankAccount(101, "Sanjita", 5000);

            System.out.println("Account Holder: " + account.getName());
            System.out.println("Initial Balance: " + account.getBalance());

            account.deposit(2000);
            System.out.println("After Deposit: " + account.getBalance());

            account.withdraw(1000);
            System.out.println("After Withdrawal: " + account.getBalance());

        
             account.deposit(-500);  
             account.withdraw(10000); 

        } catch (NegativeAmountException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
	


