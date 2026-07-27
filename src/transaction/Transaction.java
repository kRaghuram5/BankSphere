package transaction;
import account.*;
import java.time.LocalDateTime;

public class Transaction {
    private static int transactionId=0;
    private final Account senderAccount;
    private final Account receiverAccount;
    private final int  amount;
    private final TransactionType type;
    private TransactionStatus status;
    private final LocalDateTime timestamp;

    public Transaction(Account senderAccount, Account receiverAccount, int amount, TransactionType type ){
        transactionId++;
        this.senderAccount=senderAccount;
        this.receiverAccount=receiverAccount;
        this.amount=amount;
        this.type=type;
        status = TransactionStatus.PENDING;
        timestamp = LocalDateTime.now();
    }

    public void displayTransaction(){
        System.out.println("Transaction ID: " + transactionId + "\nFrom : "+ senderAccount.getAccountNumber() +
                "\nTo : " + receiverAccount.getAccountNumber() + "\nAmount : " + amount + "\nStatus : "
                + status + "\nType: " + type + "\nTime : " + timestamp
        );
    }

    public void setStatus(TransactionStatus status){
        this.status = status;
    }
}
