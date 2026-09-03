package com.banksphere;

import java.util.List;
import java.util.Scanner;

import javax.security.auth.login.AccountNotFoundException;

import com.banksphere.account.Account;
import com.banksphere.account.CurrentAccount;
import com.banksphere.account.SavingsAccount;
import com.banksphere.bank.Bank;
import com.banksphere.bank.Branch;
import com.banksphere.customer.Customer;
import com.banksphere.enums.AccountType;
import com.banksphere.exception.AccountInactiveException;
import com.banksphere.exception.CustomerNotFoundException;
import com.banksphere.exception.InsufficientBalanceException;
import com.banksphere.exception.InvalidAmountException;
import com.banksphere.repository.AccountRepository;
import com.banksphere.repository.BranchRepository;
import com.banksphere.repository.CustomerRepository;
import com.banksphere.repository.TransactionRepository;
import com.banksphere.transaction.Transaction;
import com.banksphere.transaction.TransactionStatus;
import com.banksphere.transaction.TransactionType;

/**
 * BankMenu — console UI for BankSphere.
 *
 * All operations now persist to the database via the Repository layer.
 * After every deposit / withdraw / transfer the updated account is
 * merged back via AccountRepository.update() so balances survive a restart.
 */
public class BankMenu {

    // ── Repositories ─────────────────────────────────────────────────────────
    private static final CustomerRepository    customerRepo    = new CustomerRepository();
    private static final AccountRepository     accountRepo     = new AccountRepository();
    private static final TransactionRepository transactionRepo = new TransactionRepository();
    private static final BranchRepository      branchRepo      = new BranchRepository();

    public static void start(Bank bank) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""

                    ╔══════════════════════════════╗
                    ║       B A N K S P H E R E    ║
                    ║   Smart Banking, Simplified  ║
                    ╠══════════════════════════════╣
                    ║  1. Create Customer          ║
                    ║  2. Open Account             ║
                    ║  3. Deposit                  ║
                    ║  4. Withdraw                 ║
                    ║  5. Transfer                 ║
                    ║  6. Display All Customers    ║
                    ║  7. Display All Accounts     ║
                    ║  8. Transaction History      ║
                    ║  9. Exit                     ║
                    ╚══════════════════════════════╝""");

            System.out.print("  Choice : ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Please enter a valid number.");
                continue;
            }

            switch (choice) {

                // ── 1. Create Customer ────────────────────────────────────────
                case 1 -> {
                    try {
                        System.out.print("  Name    : ");
                        String name = scanner.nextLine().trim();

                        System.out.print("  Age     : ");
                        int age = Integer.parseInt(scanner.nextLine().trim());

                        System.out.print("  Phone   : ");
                        long phone = Long.parseLong(scanner.nextLine().trim());

                        System.out.print("  Address : ");
                        String address = scanner.nextLine().trim();

                        System.out.println("  Select Branch → 1. Mysore (BKS0001)   2. Bangalore (BKS0002)");
                        System.out.print("  Branch Choice : ");
                        int branchChoice = Integer.parseInt(scanner.nextLine().trim());

                        String ifsc = branchChoice == 1 ? "BKS0001" : "BKS0002";
                        Branch branch = branchRepo.findByIfsc(ifsc)
                                .orElseThrow(() -> new RuntimeException("Branch not found"));

                        Customer customer = new Customer(name, age, phone, branch, address);
                        customerRepo.save(customer);

                        System.out.println("\n  ✔ Customer created successfully!");
                        System.out.println(customer);
                    } catch (Exception e) {
                        System.out.println("  ✘ Error: " + e.getMessage());
                    }
                }

                // ── 2. Open Account ───────────────────────────────────────────
                case 2 -> {
                    try {
                        System.out.print("  Customer ID     : ");
                        String id = scanner.nextLine().trim();

                        Customer customer = customerRepo.findById(id)
                                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + id));

                        System.out.println("  Account Type → 1. Savings   2. Current");
                        System.out.print("  Type Choice       : ");
                        int type = Integer.parseInt(scanner.nextLine().trim());

                        System.out.print("  Initial Deposit   : ₹");
                        int balance = Integer.parseInt(scanner.nextLine().trim());

                        Account account;
                        if (type == 1)
                            account = new SavingsAccount(customer, AccountType.SAVING_ACCOUNT, balance);
                        else
                            account = new CurrentAccount(customer, AccountType.CURRENT_ACCOUNT, balance);

                        accountRepo.save(account);

                        System.out.println("\n  ✔ Account opened successfully!");
                        account.displayAccount();
                    } catch (CustomerNotFoundException e) {
                        System.out.println("  ✘ " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("  ✘ Error: " + e.getMessage());
                    }
                }

                // ── 3. Deposit ────────────────────────────────────────────────
                case 3 -> {
                    try {
                        System.out.print("  Account Number : ");
                        int accNo = Integer.parseInt(scanner.nextLine().trim());

                        System.out.print("  Deposit Amount : ₹");
                        int amount = Integer.parseInt(scanner.nextLine().trim());

                        Account account = accountRepo.findByAccountNumber(accNo)
                                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accNo));

                        account.deposit(amount);
                        accountRepo.update(account);   // ← persist balance change

                        // Record transaction
                        Transaction txn = new Transaction(account, account, amount, TransactionType.DEPOSIT);
                        txn.setStatus(TransactionStatus.SUCCESS);
                        transactionRepo.save(txn);

                        System.out.println("\n  ✔ Deposit successful!");
                        account.displayAccount();
                    } catch (AccountNotFoundException | InvalidAmountException | AccountInactiveException e) {
                        System.out.println("  ✘ " + e.getMessage());
                    }
                }

                // ── 4. Withdraw ───────────────────────────────────────────────
                case 4 -> {
                    try {
                        System.out.print("  Account Number  : ");
                        int accNo = Integer.parseInt(scanner.nextLine().trim());

                        System.out.print("  Withdraw Amount : ₹");
                        int amount = Integer.parseInt(scanner.nextLine().trim());

                        Account account = accountRepo.findByAccountNumber(accNo)
                                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accNo));

                        account.withdraw(amount);
                        accountRepo.update(account);   // ← persist balance change

                        // Record transaction
                        Transaction txn = new Transaction(account, account, amount, TransactionType.WITHDRAW);
                        txn.setStatus(TransactionStatus.SUCCESS);
                        transactionRepo.save(txn);

                        System.out.println("\n  ✔ Withdrawal successful!");
                        account.displayAccount();
                    } catch (AccountNotFoundException | InvalidAmountException |
                             InsufficientBalanceException | AccountInactiveException e) {
                        System.out.println("  ✘ " + e.getMessage());
                    }
                }

                // ── 5. Transfer ───────────────────────────────────────────────
                case 5 -> {
                    Transaction txn = null;
                    try {
                        System.out.print("  Sender Account   : ");
                        int senderNo = Integer.parseInt(scanner.nextLine().trim());

                        System.out.print("  Receiver Account : ");
                        int receiverNo = Integer.parseInt(scanner.nextLine().trim());

                        System.out.print("  Amount           : ₹");
                        int amount = Integer.parseInt(scanner.nextLine().trim());

                        Account sender   = accountRepo.findByAccountNumber(senderNo)
                                .orElseThrow(() -> new AccountNotFoundException("Sender account not found"));
                        Account receiver = accountRepo.findByAccountNumber(receiverNo)
                                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found"));

                        // Create PENDING record first
                        txn = new Transaction(sender, receiver, amount, TransactionType.TRANSFER);
                        transactionRepo.save(txn);

                        sender.transfer(receiver, amount);

                        // Persist both balances
                        accountRepo.update(sender);
                        accountRepo.update(receiver);

                        txn.setStatus(TransactionStatus.SUCCESS);
                        transactionRepo.update(txn);

                        System.out.println("\n  ✔ Transfer successful!");
                        txn.displayTransaction();
                    } catch (AccountNotFoundException | InvalidAmountException |
                             InsufficientBalanceException | AccountInactiveException e) {
                        System.out.println("  ✘ " + e.getMessage());
                        if (txn != null) {
                            txn.setStatus(TransactionStatus.FAILED);
                            transactionRepo.update(txn);
                        }
                    }
                }

                // ── 6. Display All Customers ──────────────────────────────────
                case 6 -> {
                    List<Customer> customers = customerRepo.findAllActive();
                    if (customers.isEmpty()) {
                        System.out.println("  No active customers found.");
                    } else {
                        System.out.println("\n  ── Active Customers ──────────────────────────────");
                        customers.forEach(c -> { System.out.println(c); System.out.println(); });
                    }
                }

                // ── 7. Display All Accounts ───────────────────────────────────
                case 7 -> {
                    List<Account> accounts = accountRepo.findAll();
                    if (accounts.isEmpty()) {
                        System.out.println("  No accounts found.");
                    } else {
                        System.out.println("\n  ── All Accounts ──────────────────────────────────");
                        accounts.forEach(a -> { a.displayAccount(); System.out.println(); });
                    }
                }

                // ── 8. Transaction History ────────────────────────────────────
                case 8 -> {
                    try {
                        System.out.print("  Account Number (or 0 for ALL) : ");
                        int accNo = Integer.parseInt(scanner.nextLine().trim());
                        List<Transaction> txns = accNo == 0
                                ? transactionRepo.findAll()
                                : transactionRepo.findByAccountNumber(accNo);
                        if (txns.isEmpty()) {
                            System.out.println("  No transactions found.");
                        } else {
                            System.out.println("\n  ── Transaction History ───────────────────────────");
                            txns.forEach(t -> { t.displayTransaction(); System.out.println(); });
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("  ✘ Invalid account number.");
                    }
                }

                // ── 9. Exit ───────────────────────────────────────────────────
                case 9 -> {
                    running = false;
                    System.out.println("\n  Thank you for using BankSphere. Goodbye! 👋");
                }

                default -> System.out.println("  ⚠ Invalid choice. Please select 1–9.");
            }
        }
        scanner.close();
    }
}
