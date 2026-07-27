import account.*;
import bank.*;
import customer.*;
import enums.*;
import exception.*;
import transaction.*;
import transaction.Transaction;
import java.util.Scanner;
public class BankMenu {
    public static void start(Bank bank){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("""
                    ========= BANKSPHERE =========
                    1. Create Customer
                    2. Open Account
                    3. Deposit
                    4. Withdraw
                    5. Transfer
                    6. Display Customers
                    7. Display Accounts
                    8. Exit
                    """);

            System.out.print("Choice : ");
            int choice = scanner.nextInt();

            switch(choice){
                //-------------------------------
                case 1 -> {
                    System.out.print("Customer Name, Age, Phone, Address ");
                    String name = scanner.nextLine();
                    int age = scanner.nextInt();
                    long phone = scanner.nextLong();
                    String address = scanner.nextLine();
                    System.out.println(" Select Branch 1. Mysore 2. Bangalore");
                    int branchChoice = scanner.nextInt();
                    Branch branch = null;
                    if(branchChoice == 1)
                        branch = bank.getBranch("BKS0001");
                    else if(branchChoice == 2)
                        branch = bank.getBranch("BKS0002");
                    Customer customer = new Customer(name,age,phone,branch,address);
                    bank.addCustomer(customer);
                    System.out.println("Customer Created Successfully");
                    System.out.println(customer);
                }
                //-------------------------------
                case 2 -> {
                    scanner.nextLine();
                    System.out.print("Enter Customer ID : ");
                    String id = scanner.nextLine();
                    try{
                        Customer customer = bank.getCustomerById(id);
                        System.out.println(" Select 1. Savings 2. Current ");
                        int type = scanner.nextInt();
                        System.out.print("Initial Deposit : ");
                        int balance = scanner.nextInt();
                        Account account;
                        if(type==1)
                            account = new SavingsAccount( customer, AccountType.SAVING_ACCOUNT, balance );
                        else
                            account = new CurrentAccount( customer, AccountType.CURRENT_ACCOUNT, balance );
                        bank.openAccount(account);
                        System.out.println("Account Opened Successfully");
                        account.displayAccount();
                    }
                    catch(CustomerNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                }
                //-------------------------------
                case 3 -> {
                    System.out.print("Account Number : ");
                    int accNo = scanner.nextInt();
                    System.out.print("Deposit Amount : ");
                    int amount = scanner.nextInt();
                    try{
                        Account account = bank.getAccountByNumber(accNo);
                        account.deposit(amount);
                        System.out.println("Deposit Successful");
                        account.displayAccount();
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                //-------------------------------
                case 4 -> {
                    System.out.print("Account Number : ");
                    int accNo = scanner.nextInt();
                    System.out.print("Withdraw Amount : ");
                    int amount = scanner.nextInt();
                    try{
                        Account account = bank.getAccountByNumber(accNo);
                        account.withdraw(amount);
                        System.out.println("Withdraw Successful");
                        account.displayAccount();
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                //-------------------------------
                case 5 -> {
                    System.out.print("Sender Account : ");
                    int sender = scanner.nextInt();
                    System.out.print("Receiver Account : ");
                    int receiver = scanner.nextInt();
                    System.out.print("Amount : ");
                    int amount = scanner.nextInt();
                    try{
                        Account senderAcc = bank.getAccountByNumber(sender);
                        Account receiverAcc = bank.getAccountByNumber(receiver);
                        Transaction transaction =
                                new transaction.Transaction(
                                        senderAcc,
                                        receiverAcc,
                                        amount,
                                        TransactionType.TRANSFER
                                );
                        senderAcc.transfer(receiverAcc,amount);
                        transaction.setStatus(TransactionStatus.SUCCESS);
                        System.out.println("Transfer Successful");
                        transaction.displayTransaction();
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                //-------------------------------
                case 6 -> bank.displayAllCustomers();
                //-------------------------------
                case 7 -> bank.displayAllAccounts();
                //-------------------------------
                case 8 ->{
                    running = false;
                    System.out.println("Thank You For Using BankSphere");
                }
                default -> System.out.println("Invalid Choice");
            }
        }
    }
}