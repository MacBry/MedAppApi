package pl.mac.bry.exceptions;

public class CannotCreateOrderForPatientWithoutAddressException extends RuntimeException {
    private static final String MSG = "Can't create order for Patient without address";

    public CannotCreateOrderForPatientWithoutAddressException() {
        super(MSG);
    }
}
