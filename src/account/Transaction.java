package account;

import exception.*;

public interface Transaction {
    void deposit(int amount) throws InvalidAmountException,AccountInactiveException;
    void withdraw(int amount)  throws InvalidAmountException, InsufficientBalanceException,AccountInactiveException;
    void transfer(Account receiver, int amount) throws InvalidAmountException,InsufficientBalanceException, AccountInactiveException;
}
