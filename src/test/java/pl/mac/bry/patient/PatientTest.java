package pl.mac.bry.patient;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        patient = new Patient();
    }

    @Test
    void newPatientShouldHaveEmptyOrderSet() {
        assertThat(patient.getOrders(), is(empty()));
    }

    @Test
    void newPatientShouldHaveEmptyAddressSet() {
        assertThat(patient.getPatientAddresses(), is(empty()));
    }
}
