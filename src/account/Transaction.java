package account;

import exception.*;

public interface Transaction {
    void deposit(int amount) throws InvalidAmountException;
    void withdraw(int amount)  throws InvalidAmountException, InsufficientBalanceException;
    void transfer(Account receiver, int amount) throws InvalidAmountException,InsufficientBalanceException;
}
