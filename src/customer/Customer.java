package customer;

import account.Account;
import bank.Branch;

import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private final String customerId;
    private String status;
    private final Branch branch;
    private static int totalCustomersCreated =0;
    private final List<Account> accounts;

    public Customer( String name, int age, long phone, Branch branch, String address){
        super(name,age,phone,address);
        totalCustomersCreated++;
        this.customerId="CID"+ totalCustomersCreated;
        this.status="ACTIVE";
        this.branch = branch;

        accounts = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Customer : " +
                "CustomerId=" + customerId + "\n" +
                super.toString() +
                branch.getBranchSummary() +
                ", Status=" + status;
    }

    public Branch getBranch(){
        return branch;
    }

    public String getCustomerId(){
        return customerId;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status=status;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }
}
