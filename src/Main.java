import bank.Bank;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
        System.out.println(Bank.getTotalBanksCreated()); //Bank.showTotalBanks();

        //Display Branch
        bank1.displayAllBranch();
        bank2.displayAllBranch();
        System.out.println(bank1.getTotalBranch());




    }
}