package exceptions;

public class StockInsufficientException extends Exception {
    public StockInsufficientException(String message) {
        super(message);
    }
}
