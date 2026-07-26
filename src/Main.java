import bank.*;
import customer.*;
import account.*;
import exception.*;

public class Main {
    public static void main(String[] args) {
        //Add Bank
        Bank bank1 = new Bank("BS001", "BankSphere", "Mysuru", 2026);
//        Bank bank2 = new Bank("BS002","BankSphere","Chennai",2026);

        //Add Branch
        Branch mysore = new Branch("Mysuru", "BKS0001", "JPnagar,Mysore", bank1);
        Branch banglore = new Branch("Banglore", "BKS0002", "Vpuram,Banglore", bank1);
        bank1.addBranch(mysore);
//        System.out.println(mysore.getBank());
        bank1.addBranch(banglore);
//        Branch chennai = new Branch("Chennai", "BTN0001", "Egmore,Chennai", bank2);
//        bank2.addBranch(chennai);
        //Display Bank
        System.out.println(bank1);
//        System.out.println(bank2);
//        Bank.showTotalBanks();

        //Display Branch
//        bank1.displayAllBranch();
//        bank2.displayAllBranch();
//        System.out.println(bank1.getTotalBranches());

        //Add Customers

        Customer raghu = new Customer("Raghu", 21, +999_99_99_999L, bank1.getBranch("BKS0001"), "Mysuru");
        bank1.addCustomer(raghu);

        Customer Alex = new Customer("Alex", 30, +998_76_86_888L, bank1.getBranch("BKS0002"), "Banglore");
        bank1.addCustomer(Alex);
//
//        bank1.displayAllCustomers();
//        Branch branch = bank1.getBranch("BKS0001");
//        branch.displayAllCustomers();

//        Person p = new Customer(
//                "Raghu",
//                21,
//                9999999999L,
//                bank1.getBranch("BKS0001"),
//                "Mysuru"
//        );
//        bank1.removeCustomer(raghu);
//        System.out.println(p);
//        acc.getInterestRate(); wont work, because acc is Parent class , method is child class.
//        if(acc instanceof SavingsAccount) {
//            SavingsAccount s = (SavingsAccount) acc;
//            System.out.println(s.getInterestRate());
//        }
        Account acc1 = new SavingsAccount(raghu, 1000);
        Account acc2 = new CurrentAccount(Alex, 10000);
        bank1.openAccount(acc1);
        bank1.openAccount(acc2);
        try {
            acc1.transfer(acc2, 1000);
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println(e);
        }
        try {
            acc1.deposit(1000);
            acc2.withdraw(500);
            acc1.transfer(acc2, 499);
            System.out.println("Transferred Successfully");

        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println(e);
        }
    }
}