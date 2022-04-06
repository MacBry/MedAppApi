package pl.mac.bry.exceptions;

public class InvalidUnitIdException extends RuntimeException {

    private static final String MSG = "Error while getting referral unit  with id: ";

    public InvalidUnitIdException(Long unitId) {
        super(MSG + unitId);
    }
}
