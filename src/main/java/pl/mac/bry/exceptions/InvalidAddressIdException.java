package pl.mac.bry.exceptions;

public class InvalidAddressIdException extends RuntimeException {
    private static final String MSG = "Error while getting address with id: ";
    public InvalidAddressIdException(Long addressId) {
        super(MSG  + addressId);
    }
}
