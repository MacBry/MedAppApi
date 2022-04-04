package pl.mac.bry.exceptions;

import pl.mac.bry.patient.Patient;

public class CannotCreateOrderForPatientException extends RuntimeException {
    private static final String ADDRESS_MSG = "Can't  create order for a Patient without an assigned address!";
    private static final String ORDER_STATUS_MSG ="Can't  create order for a Patient with unrealized order";
    private static final String GENERAL_MSG = "Can't create order!";

    public CannotCreateOrderForPatientException(Patient patient) {
        super(getMsgByReason(patient));
    }

    private static String getMsgByReason(Patient patient) {
        if(patient.getAddresses().isEmpty()) {
            return ADDRESS_MSG;
        }
        else return GENERAL_MSG;
    }
}
