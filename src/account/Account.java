package account;

import bank.*;
import customer.*;
import exception.*;
import enums.*;

public abstract class Account implements Transaction, Comparable<Account> {
    private final int accountNumber;
    private static int totalAccountsCreated = 0;
    private int balance;
    private final Customer customer;
    private final Branch branch;
    private AccountStatus status;
    private final AccountType type;

    public Account(Customer customer, AccountType type,int initialBalance){
        accountNumber = ++totalAccountsCreated;
        balance = initialBalance;
        this.branch = customer.getBranch();
        this.customer = customer;
        this.type=type;
        this.status = AccountStatus.ACTIVE;
    }

    public abstract void deposit(int amount) throws InvalidAmountException, AccountInactiveException;

    public abstract void withdraw(int amount) throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException;

    public abstract void transfer(Account receiver, int amount)throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException;

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
                "\n Type :" + type +
                "\nStatus : " + status
         );
    }
    public AccountType getAccountType(){
        return type;
    }
    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status){
        this.status = status;
    }

    @Override
    public int compareTo(Account other) {
        return Integer.compare(this.accountNumber, other.accountNumber);
    }
}
