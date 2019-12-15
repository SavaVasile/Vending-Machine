package vendingmachine.exceptions;

public class NotFullPaidException extends RuntimeException {
    public NotFullPaidException(String message){
        super("Not full paid");
    }
    public NotFullPaidException() {
        super();
    }
}
