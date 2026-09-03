package com.banksphere.customer;

import com.banksphere.account.Account;
import com.banksphere.bank.Branch;
import com.banksphere.enums.CustomerStatus;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer entity — persisted in the `customers` table.
 *
 * Inherits name/age/phone/address columns from {@link Person} via @MappedSuperclass.
 * Belongs to a Branch (@ManyToOne).
 * Owns zero or more Accounts (@OneToMany).
 */
@Entity
@Table(name = "customers")
public class Customer extends Person {

    /** Auto-generated surrogate PK (e.g. CID1, CID2 …) */
    @Id
    @Column(name = "customer_id", nullable = false, length = 20)
    private String customerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CustomerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_ifsc", nullable = false)
    private Branch branch;

    /**
     * One customer may have multiple accounts.
     * mappedBy refers to Account.customer field.
     * CascadeType.ALL means saving a customer can cascade to accounts.
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

    /** Sequence helper — not stored in DB (static stays in-memory per run) */
    private static int counter = 0;

    /** No-arg constructor required by JPA. */
    protected Customer() {}

    public Customer(String name, int age, long phone, Branch branch, String address) {
        super(name, age, phone, address);
        counter++;
        this.customerId = "CID" + counter;
        this.status     = CustomerStatus.ACTIVE;
        this.branch     = branch;
    }

    // ── Getters ─────────────────────────────────────────────────────────────

    public String getCustomerId()  { return customerId; }
    public CustomerStatus getStatus() { return status; }
    public Branch getBranch()      { return branch; }
    public List<Account> getAccounts() { return accounts; }

    // ── Mutators ─────────────────────────────────────────────────────────────

    public void setStatus(CustomerStatus status) { this.status = status; }

    public void addAccount(Account account) { accounts.add(account); }

    // ── Display ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Customer : " +
               "CustomerId=" + customerId + "\n" +
               super.toString() + "\n" +
               branch.getBranchSummary() +
               ", Status=" + status;
    }
}
