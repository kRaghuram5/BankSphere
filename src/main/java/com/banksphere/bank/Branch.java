package com.banksphere.bank;

import com.banksphere.account.Account;
import com.banksphere.customer.Customer;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Branch entity — persisted in the `branches` table.
 *
 * Each Branch belongs to one Bank (@ManyToOne).
 * A Branch has many Customers and many Accounts (@OneToMany).
 */
@Entity
@Table(name = "branches")
public class Branch {

    /** IFSC code used as the natural primary key (e.g. BKS0001). */
    @Id
    @Column(name = "ifsc", nullable = false, length = 20)
    private String ifsc;

    @Column(name = "branch_name", nullable = false, length = 100)
    private String branchName;

    @Column(nullable = false, length = 200)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers = new ArrayList<>();

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts = new ArrayList<>();

    /** No-arg constructor required by JPA. */
    protected Branch() {}

    public Branch(String branchName, String ifsc, String address, Bank bank) {
        this.branchName = branchName;
        this.ifsc       = ifsc;
        this.address    = address;
        this.bank       = bank;
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    public void addCustomer(Customer customer) { customers.add(customer); }

    public void addAccount(Account account) { accounts.add(account); }

    public void displayAllCustomers() {
        customers.forEach(c -> { System.out.println(c); System.out.println(); });
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getIfsc()          { return ifsc; }
    public String getBranchName()    { return branchName; }
    public String getAddress()       { return address; }
    public Bank getBank()            { return bank; }
    public List<Customer> getCustomers() { return customers; }
    public List<Account> getAccounts()   { return accounts; }

    // ── Display ──────────────────────────────────────────────────────────────

    public String getBranchSummary() {
        return "Branch : " + branchName + " - ( " + ifsc + " )\nBank: " + bank.getBankName();
    }

    @Override
    public String toString() {
        return "Branch: \n ========================\n" +
               "branchName=" + branchName + ',' +
               bank.getBankName() + '\n' +
               "IFSC="         + ifsc    + ",\n" +
               "address="      + address + '\n' +
               "========================";
    }
}
