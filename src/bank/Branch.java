package bank;


import account.*;
import customer.*;

import java.util.*;

public class Branch {
    private final String branchName;
    private final String IFSC;
    private final String address;
    private final Bank bank;
    private final List<Customer> customers;

    private final List<Account> accounts;

    public Branch(String branchName,String IFSC,String address, Bank bank){
        this.branchName = branchName;
        this.IFSC = IFSC;
        this.address = address;
        this.bank = bank;
        customers = new ArrayList<>();

        accounts = new ArrayList<>();
    }

    public String getIFSC() {
        return IFSC;
    }

    @Override
    public String toString() {
        return "Branch: \n ========================\n" +
                "branchName=" + branchName + ',' +
                bank.getBankName() + '\n' +
                "IFSC=" + IFSC + ",\n" +
                "address=" + address + '\n' +
                "========================";
    }
    public String getBranchSummary(){
        return "Branch : " + branchName + " - ( " + IFSC + " )\nBank: " + bank.getBankName();
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void displayAllCustomers(){
        for(Customer customer : customers){
            System.out.println(customer);
            System.out.println();
        }
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public Bank getBank(){
        return bank;
    }
}

