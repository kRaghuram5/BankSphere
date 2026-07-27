package exception;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String str) {
      super(str);
    }
}
