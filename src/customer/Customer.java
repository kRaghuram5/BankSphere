package customer;

import bank.Branch;

public class Customer extends Person {
    private final String customerId;
    private String status;
    private final Branch branch;
    private static int totalCustomersCreated =0;
    public Customer( String name, int age, long phone, Branch branch, String address){
        super(name,age,phone,address);
        totalCustomersCreated++;
        this.customerId="CID"+ totalCustomersCreated;
        this.status="ACTIVE";
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                super.toString() +
                branch.getBranchSummary() +
                ", status=" + status +
                '}';
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
}
