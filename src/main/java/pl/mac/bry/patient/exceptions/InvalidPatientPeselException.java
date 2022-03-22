package pl.mac.bry.patient.exceptions;

public class InvalidPatientPeselException extends RuntimeException {

    private static final String MSG = "Error while getting patient with PESEL: ";

    public InvalidPatientPeselException(String pesel) {
        super(MSG + pesel);
    }
}
