import bank.*;
import customer.*;
import account.*;
import exception.*;
import transaction.*;
import transaction.Transaction;

public class Main {
    public static void main(String[] args) {
        //Add Bank
        Bank bank1 = new Bank("BS001", "BankSphere", "Mysore", 2026);
//        Bank bank2 = new Bank("BS002","BankSphere","Chennai",2026);

        //Add Branch
        Branch mysore = new Branch("Mysore", "BKS0001", "JP-Nagar,Mysore", bank1);
        Branch Bangalore = new Branch("Bangalore", "BKS0002", "V-Pura,Bangalore", bank1);
        bank1.addBranch(mysore);
//        System.out.println(mysore.getBank());
        bank1.addBranch(Bangalore);
//        Branch Chennai = new Branch("Chennai", "BTN0001", "Chennai", bank2);
//        bank2.addBranch(Chennai);
        //Display Bank
        System.out.println(bank1);
//        System.out.println(bank2);
//        Bank.showTotalBanks();

        //Display Branch
//        bank1.displayAllBranch();
//        bank2.displayAllBranch();
//        System.out.println(bank1.getTotalBranches());

        //Add Customers

        Customer Raghu = new Customer("Raghu", 21, +999_99_99_999L, bank1.getBranch("BKS0001"), "Mysore");
        bank1.addCustomer(Raghu);

        Customer Alex = new Customer("Alex", 30, +998_76_86_888L, bank1.getBranch("BKS0002"), "Bangalore");
        bank1.addCustomer(Alex);

        try{
            bank1.getCustomerId("CID999");
        }
        catch(CustomerNotFoundException e){
            System.out.println(e);
        }
//
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
        Account acc1 = new SavingsAccount(Raghu, 1000);
        Account acc2 = new CurrentAccount(Alex, 10000);
        bank1.openAccount(acc1);
        bank1.openAccount(acc2);
        Transaction t1 = new transaction.Transaction(acc2,acc1,1000,TransactionType.TRANSFER);
        try {
            acc2.transfer(acc1, 1000);
            System.out.println("Transferred Successfully");
            t1.setStatus(TransactionStatus.SUCCESS);
        } catch (InvalidAmountException | InsufficientBalanceException | AccountInactiveException e) {
            System.out.println(e);
            t1.setStatus(TransactionStatus.FAILED);
        }
        finally {
            t1.displayTransaction();
        }
        acc1.displayAccount();
        acc2.displayAccount();
    }
}