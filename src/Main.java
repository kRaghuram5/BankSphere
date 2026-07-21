import bank.Bank;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] a = new int[4];
        Bank bank1 = new Bank("BS001","BankSphere","Mysuru",2026);
        bank1.addBranch("Mysuru", "BKS0001", "Mysore");
        bank1.addBranch("Banglore", "BKS0002", "Banglore");
        bank1.addBranch("Manglore", "BKS0003", "Manglore");
        bank1.displayBankInfo();
        bank1.displayAllBranch();
        System.out.println(bank1.getTotalBranch());
        Bank bank2 = new Bank("BS002","BankSphere","Chennai",2026);
        bank2.displayBankInfo();
        System.out.println(Bank.getTotalBanksCreated());
        Bank.showTotalBanks();
    }
}