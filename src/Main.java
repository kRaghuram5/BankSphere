import bank.*;
import customer.*;

public class Main {
    public static void main(String[] args) {
        //Add Bank
        Bank bank1 = new Bank("BS001","BankSphere","Mysuru",2026);
        Bank bank2 = new Bank("BS002","BankSphere","Chennai",2026);

        //Add Branch
        Branch mysore = new Branch("Mysuru", "BKS0001", "JPnagar,Mysore", bank1);
        Branch banglore = new Branch("Banglore", "BKS0002", "Vpuram,Banglore", bank1);
        bank1.addBranch(mysore);
        System.out.println(mysore.getBank());
        bank1.addBranch(banglore);
        Branch chennai = new Branch("Chennai", "BTN0001", "Egmore,Chennai", bank2);
        bank2.addBranch(chennai);
        //Display Bank
        System.out.println(bank1);
        System.out.println(bank2);
        Bank.showTotalBanks();

        //Display Branch
        bank1.displayAllBranch();
        bank2.displayAllBranch();
        System.out.println(bank1.getTotalBranches());

        //Add Customers

        Customer raghu = new Customer("Raghu",21,+99_99_99_999,bank1.getBranch("BKS0001"),"Mysuru");
        bank1.addCustomer(raghu);

        bank1.displayAllCustomers();
        Branch branch = bank1.getBranch("BKS0001");
        branch.displayAllCustomers();

        Person p = new Customer(
                "Raghu",
                21,
                9999999999L,
                bank1.getBranch("BKS0001"),
                "Mysuru"
        );
        System.out.println(bank1.getCustomerId("CID1"));
        bank1.removeCustomer(raghu);
        System.out.println(p);
    }
}