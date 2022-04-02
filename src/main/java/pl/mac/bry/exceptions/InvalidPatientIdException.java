package pl.mac.bry.exceptions;

public class InvalidPatientIdException extends RuntimeException {

    private static final String MSG = "Error while getting patient with id: ";

    public InvalidPatientIdException(long id) {
        super(MSG + id);
    }
}
