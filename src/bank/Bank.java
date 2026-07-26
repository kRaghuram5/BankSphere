package bank;
import account.*;
import customer.*;
import exception.AccountNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static int bankCount =0;
    private final String bankId, bankName, headOffice;
    private final int established_year;

    private final List<Branch> branches;

    private final List<Customer> customers;

    private final List<Account> accounts;

    public Bank(String bankId, String bankName, String headOffice, int year){
        bankCount++;
        established_year=year;
        this.bankId = bankId;
        this.bankName = bankName;
        this.headOffice=headOffice;

        branches = new ArrayList<>();

        customers = new ArrayList<>();

        accounts = new ArrayList<>();
    }

    //Current Bank
    public String toString(){
        return ("========================\nBANK INFORMATION \n========================" +
                "\nBank Name : "+bankName +
                "\nBank Id : "+bankId +
                "\nHeadOffice : "+headOffice +
                "\nEstablished : "+established_year +
                "\nTotal Branch : "+ branches.size() +
                "\n========================"
        );
    }

    public String getBankName(){
        return bankName;
    }

    public static void showTotalBanks(){
        System.out.println("Total Banks Created : " + bankCount);
    }

    //My Branches
    public void addBranch(Branch branch){
        branches.add(branch);
    }

    public void displayAllBranch(){
        for(Branch branch : branches){
            System.out.println(branch);
        }
    }

    public Branch getBranch(String IFSC){
        for(Branch branch : branches){
            if(branch.getIFSC().equals(IFSC)){
                return branch;
            }
        }
        return null;
    }

    public int getTotalBranches(){
        return branches.size();
    }

    //My Customers
    public void addCustomer(Customer customer){
        if(customer.getBranch()!=null){
            customers.add(customer);
            customer.getBranch().addCustomer(customer);
        }
    }

    public void displayAllCustomers(){
        for(Customer customer: customers){
            if(customer.getStatus().equals("ACTIVE"))
                System.out.println(customer);
        }
    }

    public String getCustomerId(String id){
        for(Customer customer: customers){
            if(customer.getCustomerId().equals(id))
                return customer.toString();
        }
        return "Not Found";
    }

    public void removeCustomer(Customer customer){
        customer.setStatus("INACTIVE");
    }

    //Account
    public void openAccount(Account account){
        accounts.add(account);
        account.getBranch().addAccount(account);
        account.getCustomer().addAccount(account);
    }

    public void displayAllAccounts(){
        for(Account account:accounts){
            account.displayAccount();
        }
    }

    public Account getAccountByNumber (int id) throws AccountNotFoundException {
        for(Account account:accounts){
            if(account.getAccountNumber()==id)
                return account;
        }
        throw new AccountNotFoundException("Account Not found");
    }
}
