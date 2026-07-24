package bank;


import account.*;
import customer.*;

public class Branch {
    private final String branchName;
    private final String IFSC;
    private final String address;
    private final Bank bank;
    private final Customer[] customers;
    private int customerCount;

    private Account[] accounts;
    private int accountCount;

    public Branch(String branchName,String IFSC,String address, Bank bank){
        this.branchName = branchName;
        this.IFSC = IFSC;
        this.address = address;
        this.bank = bank;
        customers = new Customer[50];
        customerCount=0;

        accountCount =0;
        accounts = new Account[100];
    }

    public String getIFSC() {
        return IFSC;
    }

    @Override
    public String toString() {
        return "Branch: \n ========================\n" +
                "branchName='" + branchName + '\n' +
                bank.getBankName() +
                ", IFSC='" + IFSC + '\n' +
                ", address='" + address + '\n' +
                "========================";
    }
    public String getBranchSummary(){
        return "Branch : " + branchName + " - ( " + IFSC + " )\nBank: " + bank.getBankName();
    }

    public void addCustomer(Customer customer){
        customers[customerCount++] = customer;
    }
    public void displayAllCustomers(){
        for(int i=0;i<customerCount;i++){
            System.out.println(customers[i]);
            System.out.println();
        }
    }

    public void addAccount(Account account){
        accounts[accountCount++] = account;
    }

    public Bank getBank(){
        return bank;
    }
}

