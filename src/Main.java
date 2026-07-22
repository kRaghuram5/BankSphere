import bank.*;
import customer.*;

public class Main {
    public static void main(String[] args) {
        //Add Bank
        Bank bank1 = new Bank("BS001","BankSphere","Mysuru",2026);
        Bank bank2 = new Bank("BS002","BankSphere","Chennai",2026);

        //Add Branch
        bank1.addBranch("Mysuru", "BKS0001", "JPnagar,Mysore");
        bank1.addBranch("Banglore", "BKS0002", "Vpuram,Banglore");
        bank2.addBranch("Chennai", "BTN0001", "Egmore,Chennai");
        //Display Bank
        System.out.println(bank1);
        System.out.println(bank2);
        Bank.showTotalBanks();

        //Display Branch
        bank1.displayAllBranch();
        bank2.displayAllBranch();
        System.out.println(bank1.getTotalBranch());

        //Add Customers
        bank1.addCustomer(1,"Raghu",21,+99_99_99_999,bank1.getBranch("BKS0001"),"Mysuru");

        bank1.displayAllCustomers();
        Branch branch = bank1.getBranch("BKS0001");
        branch.displayAllCustomers();

        Person p = new Customer(
                101,
                "Raghu",
                21,
                9999999999L,
                bank1.getBranch("BKS0001"),
                "Mysuru"
        );

        System.out.println(p);
    }
}