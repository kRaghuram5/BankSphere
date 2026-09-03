package com.banksphere.bank;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Bank entity — persisted in the `banks` table.
 *
 * A Bank has one or more Branches.
 * Customers and Accounts are managed via Branch relations.
 */
@Entity
@Table(name = "banks")
public class Bank {

    @Id
    @Column(name = "bank_id", nullable = false, length = 20)
    private String bankId;

    @Column(name = "bank_name", nullable = false, length = 100)
    private String bankName;

    @Column(name = "head_office", nullable = false, length = 200)
    private String headOffice;

    @Column(name = "established_year", nullable = false)
    private int establishedYear;

    /**
     * One Bank → many Branches.
     * Cascade ALL: persist/remove branches along with bank.
     */
    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Branch> branches = new ArrayList<>();

    /** No-arg constructor required by JPA. */
    protected Bank() {}

    public Bank(String bankId, String bankName, String headOffice, int year) {
        this.bankId          = bankId;
        this.bankName        = bankName;
        this.headOffice      = headOffice;
        this.establishedYear = year;
    }

    // ── Branch helpers ───────────────────────────────────────────────────────

    public void addBranch(Branch branch) { branches.add(branch); }

    public Branch getBranch(String ifsc) {
        return branches.stream()
                .filter(b -> b.getIfsc().equals(ifsc))
                .findFirst()
                .orElse(null);
    }

    public int getTotalBranches() { return branches.size(); }

    public void displayAllBranch() { branches.forEach(System.out::println); }

    // ── Getters ──────────────────────────────────────────────────────────────

    public String getBankId()        { return bankId; }
    public String getBankName()      { return bankName; }
    public String getHeadOffice()    { return headOffice; }
    public int getEstablishedYear()  { return establishedYear; }
    public List<Branch> getBranches(){ return branches; }

    // ── Display ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "========================\nBANK INFORMATION \n========================" +
               "\nBank Name       : " + bankName         +
               "\nBank Id         : " + bankId           +
               "\nHead Office     : " + headOffice       +
               "\nEstablished     : " + establishedYear  +
               "\nTotal Branches  : " + branches.size()  +
               "\n========================";
    }
}
