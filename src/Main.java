import bank.Bank;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("BS001","BankSphere","Mysuru",2026);
        bank.addBranch("Mysuru", "BKS0001", "Mysore");
        bank.addBranch("Banglore", "BKS0002", "Banglore");
        bank.addBranch("Manglore", "BKS0003", "Manglore");
        bank.displayBankInfo();
        bank.displayBranch("BKS0002");
        System.out.println(bank.getTotalBranch());
        
    }
}