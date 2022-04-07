package pl.mac.bry.patient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PatientRepositoryLabTest {

    @Autowired
    private PatientRepository patientRepository;
    private Patient patient;
    private Patient patient1;

    @BeforeEach
    public void setUp(){

        patient = Patient.builder()
                .id(1)
                .firstName("testFirstName")
                .lastName("testLastName")
                .pesel("testPesel")
                .build();
        patient1 = Patient.builder()
                .id(2)
                .firstName("testFirstName1")
                .lastName("testLastName1")
                .pesel("testPesel1")
                .build();
        patientRepository.save(patient);
        patientRepository.save(patient1);
    }

    @AfterEach
    public void tearDown() {
        patientRepository.deleteAll();
        patient = null;
        patient1 = null;
    }



    @Test
    void findAllShouldReturnSetOfSizeTwo() {

        Set<Patient> patients = patientRepository.findAll().stream().collect(Collectors.toSet());
        assertThat(patients.size(),equalTo(2));
    }

    @Test
    void givenPatientToAddShouldReturnAddedPatient() {
        Patient persistPatient = patientRepository.getById(patient.getId());
        assertThat(persistPatient.getId(), equalTo(1L));
    }

    @Test
    void givenPatientIdShouldReturnPatientWithThisId() {
        Patient persistPatient = patientRepository.getById(patient.getId());
        assertThat(persistPatient.getId(), is(equalTo(patient.getId())));
    }

    @Test
    void givenPatientPeselShouldReturnPatientWithThisPesel() {
        Patient persistPatient = patientRepository.findPatientByPesel(patient.getPesel()).get();
        assertThat(persistPatient.getPesel(), equalTo(patient.getPesel()));
    }

    @Test
    void deleteAllPatientShouldReturnEmptyPatientSet() {
        patientRepository.deleteAll();
        Set<Patient> patients = patientRepository.findAll().stream().collect(Collectors.toSet());
        assertThat(patients.size(), is(equalTo(0)));
    }

    @Test
    void afterDeletePatientShouldNotFoundThisPatient() {

        patientRepository.delete(patient1);
        Optional optional = patientRepository.findById(patient1.getId());
        assertThat(optional.isEmpty(), is(equalTo(true)));
    }
}
