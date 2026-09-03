package com.banksphere;

import com.banksphere.bank.Bank;
import com.banksphere.bank.Branch;
import com.banksphere.repository.BankRepository;
import com.banksphere.repository.BranchRepository;
import com.banksphere.util.EnvLoader;
import com.banksphere.util.HibernateUtil;

/**
 * Main — BankSphere application entry point.
 *
 * Bootstrap order:
 *   1. Load .env → inject DB credentials as System properties
 *   2. Initialise Hibernate (creates EntityManagerFactory, runs DDL)
 *   3. Seed Bank + Branches if this is a first run (idempotent)
 *   4. Hand off to BankMenu for the console interaction loop
 *   5. Shutdown Hibernate gracefully on exit
 */
public class Main {

    public static void main(String[] args) {

        // ── Step 1: Load environment variables from .env ──────────────────────
        EnvLoader.load();

        // ── Step 2: Warm up Hibernate (validates persistence.xml, runs DDL) ───
        HibernateUtil.getEntityManagerFactory();

        // ── Step 3: Seed reference data (bank + branches) if not present ──────
        BankRepository   bankRepo   = new BankRepository();
        BranchRepository branchRepo = new BranchRepository();

        Bank bank = bankRepo.findById("BS001").orElseGet(() -> {
            Bank b = new Bank("BS001", "BankSphere", "Mysore", 2026);
            bankRepo.save(b);
            System.out.println("[Main] Bank seeded: " + b.getBankName());
            return b;
        });

        branchRepo.findByIfsc("BKS0001").orElseGet(() -> {
            Branch mysore = new Branch("Mysore", "BKS0001", "JP Nagar", bank);
            branchRepo.save(mysore);
            System.out.println("[Main] Branch seeded: Mysore (BKS0001)");
            return mysore;
        });

        branchRepo.findByIfsc("BKS0002").orElseGet(() -> {
            Branch bangalore = new Branch("Bangalore", "BKS0002", "V Puram", bank);
            branchRepo.save(bangalore);
            System.out.println("[Main] Branch seeded: Bangalore (BKS0002)");
            return bangalore;
        });

        // ── Step 4: Launch the interactive console menu ───────────────────────
        System.out.println("\n" + bank);
        BankMenu.start(bank);

        // ── Step 5: Graceful shutdown ─────────────────────────────────────────
        HibernateUtil.shutdown();
    }
}
