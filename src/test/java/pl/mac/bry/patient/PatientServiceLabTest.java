package pl.mac.bry.patient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceLabTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientDtoMapper patientDtoMapper;


    @Autowired
    @InjectMocks
    private PatientServiceImp patientServiceImp;


    private PatientDto patientDto;
    private PatientDto patientDto1;

    private Patient patient;

    private List<Patient> patientList;

    @BeforeEach
    public void setUp () {
        patientList = new ArrayList<>();
        patient = Patient.builder()
                .id(1L)
                .firstName("testFirstName")
                .lastName("testLastName")
                .pesel("testPesel")
                .build();

        patientDto = PatientDto.builder()
                .firstName("testFirstName")
                .lastName("testLastName")
                .pesel("testPesel")
                .build();
        patientDto1 = PatientDto.builder()
                .firstName("testFirstName1")
                .lastName("testLastName1")
                .pesel("testPesel1")
                .build();
        patientList.add(patient);
    }

    @AfterEach
    public void tearDown () {
        patientList.remove(patient);

        patientDto = null;
        patientDto1 = null;
    }

    @Test
    void givenPatientToAddShouldReturnAddedPatient () {
        when(patientRepository.save(any())).thenReturn(patient);
        patientServiceImp.addPatient(patientDto);
        verify(patientRepository, times(1)).save(any());
    }

    @Test
    void givenGetAllPatientsShouldReturnSetOfAllPatients () {
        patientRepository.save(patient);
        when(patientRepository.findAll()).thenReturn(patientList);
        Set<PatientDto> patientDtoSet = patientServiceImp.getAllPatients();
        assertThat(patientList.stream().collect(Collectors.toSet()).size(), equalTo(patientDtoSet.size()));
        verify(patientRepository,times(1)).save(patient);
        verify(patientRepository, times(1)).findAll();
    }
}
