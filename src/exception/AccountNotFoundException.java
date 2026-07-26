package exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String str){
        super(str);
    }
}
