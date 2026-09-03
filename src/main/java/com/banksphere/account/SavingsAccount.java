package com.banksphere.account;

import com.banksphere.customer.Customer;
import com.banksphere.enums.AccountStatus;
import com.banksphere.enums.AccountType;
import com.banksphere.exception.*;

import jakarta.persistence.*;

/**
 * SavingsAccount — extends Account with an interest rate and minimum balance.
 *
 * Stored in `savings_accounts` table (JOINED strategy).
 * Only the extra columns (interest_rate, minimum_balance) live here;
 * all base fields are in the `accounts` table.
 */
@Entity
@Table(name = "savings_accounts")
public class SavingsAccount extends Account {

    @Column(name = "interest_rate", nullable = false)
    private double interestRate;

    @Column(name = "minimum_balance", nullable = false)
    private int minimumBalance;

    /** No-arg constructor required by JPA. */
    protected SavingsAccount() {}

    public SavingsAccount(Customer customer, AccountType type, int initialBalance) {
        super(customer, type, initialBalance);
        this.interestRate  = 4.2;
        this.minimumBalance = 1000;
    }

    // ── TransactionOperations ────────────────────────────────────────────────

    @Override
    public void deposit(int amount) throws InvalidAmountException, AccountInactiveException {
        if (amount <= 0) throw new InvalidAmountException("Deposit Amount Invalid");
        if (getStatus() != AccountStatus.ACTIVE) throw new AccountInactiveException("Deposit Failed!! Account Inactive");
        setBalance(getBalance() + amount);
    }

    @Override
    public void withdraw(int amount) throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException {
        if (amount <= 0) throw new InvalidAmountException("Withdraw Amount Invalid");
        if (getStatus() != AccountStatus.ACTIVE) throw new AccountInactiveException("Withdraw Failed!! Account Inactive");
        int remaining = getBalance() - amount;
        if (remaining < minimumBalance) throw new InsufficientBalanceException("Low Balance!! Withdraw Failed (Min Balance: " + minimumBalance + ")");
        setBalance(remaining);
    }

    @Override
    public void transfer(Account receiver, int amount) throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException {
        withdraw(amount);
        receiver.deposit(amount);
    }

    @Override
    public void calculateInterest() {
        double interest = getBalance() * (interestRate / 100);
        System.out.println("Current Balance  : " + getBalance()   +
                           "\nInterest Rate  : " + interestRate   + "%" +
                           "\nInterest Earned: " + interest);
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public double getInterestRate()   { return interestRate; }
    public int getMinimumBalance()    { return minimumBalance; }
}
