package com.banksphere.account;

import com.banksphere.bank.Branch;
import com.banksphere.customer.Customer;
import com.banksphere.enums.AccountStatus;
import com.banksphere.enums.AccountType;
import com.banksphere.exception.*;

import jakarta.persistence.*;

/**
 * Account — abstract JPA entity, root of the account inheritance hierarchy.
 *
 * Inheritance strategy: JOINED — SavingsAccount and CurrentAccount each
 * get their own table containing only their extra columns, joined to
 * the accounts table on account_number.
 *
 * This is cleaner than SINGLE_TABLE (no nullable columns) and more
 * Spring-Boot-friendly than TABLE_PER_CLASS.
 */
@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account implements TransactionOperations, Comparable<Account> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private int accountNumber;

    @Column(nullable = false)
    private int balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_ifsc", nullable = false)
    private Branch branch;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false, length = 25)
    private AccountType type;

    /** No-arg constructor required by JPA. */
    protected Account() {}

    public Account(Customer customer, AccountType type, int initialBalance) {
        this.balance  = initialBalance;
        this.branch   = customer.getBranch();
        this.customer = customer;
        this.type     = type;
        this.status   = AccountStatus.ACTIVE;
    }

    // ── Abstract operations (implemented by subclasses) ──────────────────────

    public abstract void deposit(int amount)  throws InvalidAmountException, AccountInactiveException;
    public abstract void withdraw(int amount) throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException;
    public abstract void transfer(Account receiver, int amount) throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException;
    public abstract void calculateInterest();

    // ── Getters ──────────────────────────────────────────────────────────────

    public int getAccountNumber() { return accountNumber; }
    public int getBalance()       { return balance; }
    public Customer getCustomer() { return customer; }
    public Branch getBranch()     { return branch; }
    public AccountStatus getStatus()  { return status; }
    public AccountType getAccountType()  { return type; }

    // ── Protected mutator (used by subclasses only) ──────────────────────────

    protected void setBalance(int balance) { this.balance = balance; }

    public void setStatus(AccountStatus status) { this.status = status; }

    // ── Display ──────────────────────────────────────────────────────────────

    public void displayAccount() {
        System.out.println(
            "==========ACCOUNT INFORMATION==========\n" +
            "Account Number : " + accountNumber + "\n" +
            customer.toString()                  + "\n" +
            "Balance        : " + balance        + "\n" +
            "Type           : " + type           + "\n" +
            "Status         : " + status
        );
    }

    @Override
    public int compareTo(Account other) {
        return Integer.compare(this.accountNumber, other.accountNumber);
    }
}
