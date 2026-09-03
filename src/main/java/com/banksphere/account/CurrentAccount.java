package com.banksphere.account;

import com.banksphere.customer.Customer;
import com.banksphere.enums.AccountStatus;
import com.banksphere.enums.AccountType;
import com.banksphere.exception.*;

import jakarta.persistence.*;

/**
 * CurrentAccount — extends Account with an overdraft facility.
 *
 * Stored in `current_accounts` table (JOINED strategy).
 * Only the extra column (overdraft_limit) lives here;
 * all base fields are in the `accounts` table.
 */
@Entity
@Table(name = "current_accounts")
public class CurrentAccount extends Account {

    @Column(name = "overdraft_limit", nullable = false)
    private int overdraftLimit;

    /** No-arg constructor required by JPA. */
    protected CurrentAccount() {}

    public CurrentAccount(Customer customer, AccountType type, int initialBalance) {
        super(customer, type, initialBalance);
        this.overdraftLimit = 5000;
    }

    // ── TransactionOperations ────────────────────────────────────────────────

    @Override
    public void deposit(int amount) throws InvalidAmountException, AccountInactiveException {
        if (amount < 0) throw new InvalidAmountException("Deposit Amount must be greater than ZERO");
        if (getStatus() != AccountStatus.ACTIVE) throw new AccountInactiveException("Deposit Failed!! Account Inactive");
        setBalance(getBalance() + amount);
    }

    @Override
    public void withdraw(int amount) throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException {
        if (amount < 0) throw new InvalidAmountException("Withdraw Amount Cannot be Zero");
        if (getStatus() != AccountStatus.ACTIVE) throw new AccountInactiveException("Withdraw Failed!! Account Inactive");
        int remaining = getBalance() - amount;
        if (remaining < -overdraftLimit) throw new InsufficientBalanceException("Overdraft Limit Reached, Withdraw Failed (Limit: " + overdraftLimit + ")");
        setBalance(remaining);
    }

    @Override
    public void transfer(Account receiver, int amount) throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException {
        withdraw(amount);
        receiver.deposit(amount);
    }

    @Override
    public void calculateInterest() {
        System.out.println("Current Accounts do not earn interest.");
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public int getOverdraftLimit() { return overdraftLimit; }
}
