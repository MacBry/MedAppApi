package pl.mac.bry.patient;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientTest {

    @Test
    void newPatientShouldHaveEmptySampleSet() {
        Patient patient = new Patient();
        assertTrue(patient.getPatientSamples().isEmpty());
    }
}
