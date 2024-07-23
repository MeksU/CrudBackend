package pl.meksu.campaignrud.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Insufficient funds.");
    }
}