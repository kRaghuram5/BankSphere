import bank.*;
import customer.*;
import account.*;
import enums.AccountType;
import exception.*;
import transaction.*;
import transaction.Transaction;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(
                "BS001",
                "BankSphere",
                "Mysore",
                2026
        );

        Branch mysore = new Branch(
                "Mysore",
                "BKS0001",
                "JP Nagar",
                bank
        );

        Branch bangalore = new Branch(
                "Bangalore",
                "BKS0002",
                "V Puram",
                bank
        );

        bank.addBranch(mysore);
        bank.addBranch(bangalore);

        BankMenu.start(bank);

//        bank1.displayAllCustomers();
//        Branch branch = bank1.getBranch("BKS0001");
//        branch.displayAllCustomers();
//        Person p = new Customer(
//                "Raghu",
//                21,
//                9999999999L,
//                bank1.getBranch("BKS0001"),
//                "Mysore"
//        );
//        bank1.removeCustomer(Raghu);
//        System.out.println(p);
//        acc.getInterestRate(); wont work, because acc is Parent class , method is child class.
//        if(acc instanceof SavingsAccount) {
//            SavingsAccount s = (SavingsAccount) acc;
//            System.out.println(s.getInterestRate());
//        }
    }
}