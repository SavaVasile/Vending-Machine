package vendingmachine.exceptions;

public class SoldOutException extends RuntimeException {
    public  SoldOutException (String message){
        super("This item is not available!");
    }

    public SoldOutException() {
        super();
    }
}
