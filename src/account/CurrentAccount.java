package account;

import customer.Customer;
import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

public class CurrentAccount extends Account {
    private final int overdraftLimit;
    public CurrentAccount(Customer customer, int initialBalance){
        super(customer,initialBalance);
        overdraftLimit = 5000;
    }
    public void deposit(int amount) throws InvalidAmountException{
        if(amount<0) throw new InvalidAmountException("Deposit Amount must be greater than ZERO");
        setBalance(amount + getBalance());
    }

    public void withdraw(int amount)  throws InvalidAmountException, InsufficientBalanceException {
        if(amount<0) throw new InvalidAmountException("Withdraw Amount Cannot be Zero");
        int temp = getBalance() - amount;
        if(temp < -overdraftLimit) throw new InsufficientBalanceException("OverDraft Limit Reached, Withdraw Failed");
        else setBalance(temp);
    }

    public void transfer(Account receiver, int amount) throws InvalidAmountException, InsufficientBalanceException{
        withdraw(amount);
        receiver.deposit(amount);
    }

    public void calculateInterest(){
        System.out.println("CurrentSavings Do not have interests");
    }
}
