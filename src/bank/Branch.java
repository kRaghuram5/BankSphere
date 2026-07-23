package bank;


import customer.*;

public class Branch {
    private final String branchName;
    private final String IFSC;
    private final String address;
    private final Bank bank;
    private final Customer[] customers;
    private int customerCount;


    public Branch(String branchName,String IFSC,String address, Bank bank){
        this.branchName = branchName;
        this.IFSC = IFSC;
        this.address = address;
        this.bank = bank;
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
                bank.getBankName() +
                ", IFSC='" + IFSC + '\n' +
                ", address='" + address + '\n' +
                "========================";
    }
    public String getBranchSummary(){
        return "Branch : " + branchName + " - ( " + IFSC + " )\n Bank: " + bank.getBankName();
    }

    public void addCustomer(Customer customer){
        customers[customerCount++] = customer;
    }
    public void displayAllCustomers(){
        for(int i=0;i<customerCount;i++){
            System.out.println(customers[i]);
        }
    }

    public Bank getBank(){
        return bank;
    }
}

