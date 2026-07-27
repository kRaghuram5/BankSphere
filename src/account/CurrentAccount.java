package account;

import customer.Customer;
import enums.AccountStatus;
import exception.AccountInactiveException;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

public class CurrentAccount extends Account {
    private final int overdraftLimit;
    public CurrentAccount(Customer customer, int initialBalance){
        super(customer,initialBalance);
        overdraftLimit = 5000;
    }
    public void deposit(int amount) throws InvalidAmountException, AccountInactiveException {
        if(amount<0) throw new InvalidAmountException("Deposit Amount must be greater than ZERO");
        if(getStatus() != AccountStatus.ACTIVE) throw new AccountInactiveException("Deposit Failed!! Account Inactive");
        setBalance(amount + getBalance());
    }

    public void withdraw(int amount)  throws InvalidAmountException, InsufficientBalanceException,AccountInactiveException {
        if(amount<0) throw new InvalidAmountException("Withdraw Amount Cannot be Zero");
        if(getStatus() != AccountStatus.ACTIVE) throw new AccountInactiveException("Withdraw Failed!! Account Inactive");
        int temp = getBalance() - amount;
        if(temp < -overdraftLimit) throw new InsufficientBalanceException("OverDraft Limit Reached, Withdraw Failed");
        else setBalance(temp);
    }

    public void transfer(Account receiver, int amount) throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException{
        withdraw(amount);
        receiver.deposit(amount);
    }

    public void calculateInterest(){
        System.out.println("CurrentSavings Do not have interests");
    }
}
