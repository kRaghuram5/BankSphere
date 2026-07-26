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
    private int branchCount;

    private final Customer[] customers;
    private int customerCount;

    private final Account[] accounts;
    private int accountCount;

    public Bank(String bankId, String bankName, String headOffice, int year){
        bankCount++;
        established_year=year;
        this.bankId = bankId;
        this.bankName = bankName;
        this.headOffice=headOffice;

        branches = new ArrayList<>();
        branchCount = 0 ;

        customerCount=0;
        customers = new Customer[100];

        accountCount = 0;
        accounts = new Account[100];
    }

    //Current Bank
    public String toString(){
        return ("========================\nBANK INFORMATION \n========================" +
                "\nBank Name : "+bankName +
                "\nBank Id : "+bankId +
                "\nHeadOffice : "+headOffice +
                "\nEstablished : "+established_year +
                "\nTotal Branch : "+ branchCount +
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
        branchCount++;
    }

    public void displayAllBranch(){
        for(Branch branch : branches){
            System.out.println(branch);
        }
    }

    public Branch getBranch(String IFSC){
        for(int i=0;i<branchCount;i++){
            if(branches.get(i).getIFSC().equals(IFSC)){
                return branches.get(i);
            }
        }
        return null;
    }

    public int getTotalBranches(){
        return branchCount;
    }

    //My Customers
    public void addCustomer(Customer customer){
        if(customer.getBranch()!=null){
            customers[customerCount] = customer;
            customer.getBranch().addCustomer(customer);
            customerCount++;
        }
    }

    public void displayAllCustomers(){
        for(int i=0;i<customerCount;i++){
            if(customers[i].getStatus().equals("ACTIVE"))
                System.out.println(customers[i]);
        }
    }

    public String getCustomerId(String id){
        for(int i=0;i<customerCount;i++){
            if(customers[i].getCustomerId().equals(id))
                return customers[i].toString();
        }
        return "Not Found";
    }

    public void removeCustomer(Customer customer){
        customer.setStatus("INACTIVE");
    }

    //Account
    public void openAccount(Account account){
        accounts[accountCount++]= account;
        account.getBranch().addAccount(account);
        account.getCustomer().addAccount(account);
    }

    public void displayAllAccounts(){
        for(int i=0;i<accountCount;i++){
            accounts[i].displayAccount();
        }
    }

    public Account getAccountbyNumber (int id) throws AccountNotFoundException {
        for(int i=0;i<accountCount;i++){
            if(accounts[i].getAccountNumber()==id)
                return accounts[i];
        }
        throw new AccountNotFoundException("Account Not found");
    }
}
