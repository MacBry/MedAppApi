package pl.mac.bry.exceptions;

public class CannotCreateOrderForPatientWithUnrealizedOrder extends RuntimeException {
    private static final String MSG = "Can't create next order for Patient with unrealized order";

    public CannotCreateOrderForPatientWithUnrealizedOrder() {
        super(MSG);
    }
}
