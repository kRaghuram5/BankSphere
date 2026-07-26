package account;

import bank.*;
import customer.*;
import exception.*;

public abstract class Account implements Transaction {
    private final int accountNumber;
    private static int totalAccountsCreated = 0;
    private int balance;
    private final Customer customer;
    private final Branch branch;
    private final String status;
    public Account(Customer customer, int initialBalance){
        accountNumber = ++totalAccountsCreated;
        balance = initialBalance;
        this.branch = customer.getBranch();
        this.customer = customer;
        this.status = "ACTIVE";
    }

    public abstract void deposit(int amount) throws InvalidAmountException;

    public abstract void withdraw(int amount) throws InvalidAmountException, InsufficientBalanceException;

    public abstract void transfer(Account receiver, int amount)throws InvalidAmountException, InsufficientBalanceException;

    public abstract void calculateInterest();

    public int getBalance(){
        return balance;
    }

    protected void setBalance(int balance){
        this.balance = balance;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public Branch getBranch(){
        return branch;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void displayAccount(){
        System.out.println("==========ACCOUNT INFORMATION==========\nAccount Number : " + accountNumber +
                "\n" + customer.toString() +
                "\nBalance : " + balance +
                "\nStatus : " + status
         );
    }
}
