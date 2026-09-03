package com.banksphere.transaction;

import com.banksphere.account.Account;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Transaction entity — persisted in the `transactions` table.
 *
 * Records every financial event (deposit, withdrawal, transfer)
 * with timestamp, status, and references to the involved accounts.
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private int transactionId;

    /**
     * The account that initiated the operation.
     * For DEPOSIT this is the credited account; WITHDRAW — the debited one.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_account_number", nullable = false)
    private Account senderAccount;

    /**
     * The destination account for TRANSFER operations.
     * Same as senderAccount for DEPOSIT / WITHDRAW.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_account_number")
    private Account receiverAccount;

    @Column(nullable = false)
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionStatus status;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    /** No-arg constructor required by JPA. */
    protected Transaction() {}

    public Transaction(Account senderAccount, Account receiverAccount, int amount, TransactionType type) {
        this.senderAccount   = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount          = amount;
        this.type            = type;
        this.status          = TransactionStatus.PENDING;
        this.timestamp       = LocalDateTime.now();
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public int getTransactionId()          { return transactionId; }
    public Account getSenderAccount()      { return senderAccount; }
    public Account getReceiverAccount()    { return receiverAccount; }
    public int getAmount()                 { return amount; }
    public TransactionType getType()       { return type; }
    public TransactionStatus getStatus()   { return status; }
    public LocalDateTime getTimestamp()    { return timestamp; }

    // ── Mutators ─────────────────────────────────────────────────────────────

    public void setStatus(TransactionStatus status) { this.status = status; }

    // ── Display ──────────────────────────────────────────────────────────────

    public void displayTransaction() {
        System.out.println(
            "Transaction ID : " + transactionId                          + "\n" +
            "From Account   : " + senderAccount.getAccountNumber()       + "\n" +
            "To Account     : " + (receiverAccount != null ? receiverAccount.getAccountNumber() : "-") + "\n" +
            "Amount         : ₹" + amount                                 + "\n" +
            "Type           : " + type                                   + "\n" +
            "Status         : " + status                                 + "\n" +
            "Time           : " + timestamp
        );
    }
}
