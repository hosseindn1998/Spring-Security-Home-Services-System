package ir.hosseindn.exception;

public class NotEnoughAccountBalanceException extends RuntimeException{
    public NotEnoughAccountBalanceException(String message) {
        super(message);
    }
}
