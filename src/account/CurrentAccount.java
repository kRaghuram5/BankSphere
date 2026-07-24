package account;

import customer.Customer;

public class CurrentAccount extends Account {
    private int overdraftLimit;
    public CurrentAccount(Customer customer, int initialBalance){
        super(customer,initialBalance);
        overdraftLimit = 5000;
    }
    public void deposit(int amount){
        int temp = amount + getBalance();
        setBalance(temp);
    }

    public void withdraw(int amount){
        int temp = getBalance() - amount;
        if(temp < -overdraftLimit) System.out.println("Insufficient balance");
        else setBalance(temp);
    }

    public void calculateInterest(){
        System.out.println("CurrentSavings Do not have interests");
    }
}
