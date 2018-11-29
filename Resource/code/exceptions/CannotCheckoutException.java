package exceptions;

public class CannotCheckoutException extends Exception {
    public CannotCheckoutException(String message){
        super(message);
    }
}
