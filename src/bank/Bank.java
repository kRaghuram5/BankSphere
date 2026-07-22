package bank;
import customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private static int bankCount =0;
    private final String bankId, bankName, headOffice;
    private final int established_year;

    private final List<Branch> branches;
    private int branchCount;

    private Customer[] customers;
    private int customerCount;

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

    public static void showTotalBanks(){
        System.out.println("Total Banks Created : " + bankCount);
    }

    //My Branches
    public void addBranch(String branchName, String IFSC, String Address){

        branches.add(new Branch(branchName,IFSC,Address));
        branchCount++;
    }

    public void displayAllBranch(){
        for(Branch branch : branches){
            System.out.println(branch);;
        }
    }

    public Branch getBranch(String IFSC){
        for(int i=0;i<branchCount;i++){
            if(branches.get(i).getIFSC() == IFSC){
                return branches.get(i);
            }
        }
        return null;
    }

    public int getTotalBranch(){
        return branchCount;
    }

    //My Customers
    public void addCustomer(int customerId, String name, int age, long phone, Branch branch, String address){
        if(branch!=null){
            customers[customerCount++] = new Customer(customerId,name,age,phone,branch,address);
            branch.addCustomers(customers[customerCount-1]);
        }
    }

    public void displayAllCustomers(){
        for(int i=0;i<customerCount;i++){
            if(!customers[i].getStatus()) continue;
            System.out.println(customers[i]);
        }
    }

    public String getCustomerId(int id){
        for(int i=0;i<customerCount;i++){
            if(customers[i].getCustomerId() == id)
                return customers[i].toString();
        }
        return "Not Found";
    }

    public void remove(Customer customer){
        customer.setStatus();
    }



}
