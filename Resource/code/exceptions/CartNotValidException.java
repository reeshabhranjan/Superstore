package exceptions;

public class CartNotValidException extends Exception {
    public CartNotValidException(String message) {
        super(message);
    }
}
