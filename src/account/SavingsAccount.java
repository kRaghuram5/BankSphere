package account;

import customer.*;
import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

public class SavingsAccount extends Account{
    private final double interestRate;
    private final int minimumBalance;
    public SavingsAccount( Customer customer, int initialBalance){
        super(customer,initialBalance);
        minimumBalance = 1000;
        interestRate = 4.2;
    }
    public void deposit(int amount) throws InvalidAmountException {
        if(amount<=0) throw new InvalidAmountException("Deposit Amount Invalid");
        int temp = amount + getBalance();
        setBalance(temp);
    }

    public void withdraw(int amount) throws InvalidAmountException, InsufficientBalanceException {
        if(amount<=0) throw new InvalidAmountException("Withdraw Amount Invalid");
        int temp = getBalance() - amount;
        if(temp< minimumBalance) throw new InsufficientBalanceException("Low Balance!!, Withdraw Failed");
        else setBalance(temp);
    }

    public void transfer(Account receiver, int amount) throws InvalidAmountException, InsufficientBalanceException {
        withdraw(amount);
        receiver.deposit(amount);
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
