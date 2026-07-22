package bank;

import customer.Customer;

public class Branch {
    private final String branchName;
    private final String IFSC;
    private final String address;

    private Customer[] customers;
    private int customerCount;


    public Branch(String branchName,String IFSC,String address){
        this.branchName = branchName;
        this.IFSC = IFSC;
        this.address = address;
        customers = new Customer[50];
        customerCount=0;
    }

    public String getIFSC() {
        return IFSC;
    }

    @Override
    public String toString() {
        return "Branch: \n ========================\n" +
                "branchName='" + branchName + '\n' +
                ", IFSC='" + IFSC + '\n' +
                ", address='" + address + '\n' +
                "========================";
    }

    public void addCustomers(Customer customer){
        customers[customerCount++] = customer;
    }
    public void displayAllCustomers(){
        for(int i=0;i<customerCount;i++){
            System.out.println(customers[i]);
        }
    }
}

