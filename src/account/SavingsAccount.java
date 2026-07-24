package account;

import customer.*;

public class SavingsAccount extends Account{
    private final double interestRate;
    private final int minimumBalance;
    public SavingsAccount( Customer customer, int initialBalance){
        super(customer,initialBalance);
        minimumBalance = 1000;
        interestRate = 4.2;
    }
    public void deposit(int amount){
        int temp = amount + getBalance();
        setBalance(temp);
    }

    public void withdraw(int amount){
        int temp = getBalance() - amount;
        if(temp< minimumBalance) System.out.println("Insufficient balance");
        else setBalance(temp);
    }

    public void calculateInterest(){
        double temp = getBalance() * (interestRate/100);
        System.out.println("Current Balance : " + getBalance() +
                "\nInterest Rate : " + interestRate + "%\nInterest Earned : " + temp);
    }

    public double getInterestRate(){
        return interestRate;
    }
}
