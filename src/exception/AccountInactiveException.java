package exception;

public class AccountInactiveException extends Exception {
    public AccountInactiveException(String str) {
        super(str);
    }
}
