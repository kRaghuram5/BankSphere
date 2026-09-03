package com.banksphere.account;

import com.banksphere.exception.*;

/**
 * TransactionOperations — defines the core banking operations every account must support.
 *
 * (Previously named "Transaction" in the account package — renamed to avoid
 *  collision with the transaction.Transaction JPA entity.)
 */
public interface TransactionOperations {
    void deposit(int amount)  throws InvalidAmountException, AccountInactiveException;
    void withdraw(int amount) throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException;
    void transfer(Account receiver, int amount) throws InvalidAmountException, InsufficientBalanceException, AccountInactiveException;
}
