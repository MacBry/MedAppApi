package pl.mac.bry.patient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;
    private Patient patient;
    private Patient patient1;

    @BeforeEach
    void setUp(){
        patient = Patient.builder()
                .id(1L)
                .firstName("testFirstName")
                .lastName("testLastName")
                .pesel("testPesel")
                .build();
        patient1 = Patient.builder()
                .id(2L)
                .firstName("testFirstName1")
                .lastName("testLastName1")
                .pesel("testPesel1")
                .build();
    }

    @AfterEach
    void tearDown() {
        patient = null;
        patientRepository.deleteAll();
    }



    @Test
    void findAllShouldReturnSetOfSizeTwo() {
        patientRepository.save(patient);
        patientRepository.save(patient1);
        Set<Patient> patients = patientRepository.findAll().stream().collect(Collectors.toSet());
        assertThat(patients.size(),equalTo(2));
    }

    @Test
    void givenPatientToAddShouldReturnAddedPatient() {
        patientRepository.save(patient);
        Patient persistPatient = patientRepository.getById(patient.getId());
        assertThat(persistPatient.getId(), equalTo(1L));
    }
}
