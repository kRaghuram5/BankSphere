package customer;

import bank.Branch;

public class Customer extends Person {
    private int customerId;
    private boolean status;
    private Branch branch;
    public Customer(int customerId, String name, int age, long phone, Branch branch, String address){
        super(name,age,phone,address);
        this.customerId=customerId;
        this.status=true;
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                super.toString() +
                branch.toString() +
                ", status=" + status +
                '}';
    }

    public int getCustomerId(){
        return customerId;
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus() {
        status=false;
    }
}
