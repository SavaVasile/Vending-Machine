package vendingmachine.exceptions;

public class NoSufficientChangeException extends RuntimeException {
    public NoSufficientChangeException(String message) {
        super("There is not sufficient change!");
    }

    public NoSufficientChangeException() {
        super();
    }
}
