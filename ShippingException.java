// I use custom exceptions (Chapter 7: Error Handling) to separate error logic from the happy path.
public class ShippingException extends Exception{
    public ShippingException(String message){
        super(message);
    }
}
