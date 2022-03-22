package pl.mac.bry.patient;

import pl.mac.bry.patient.dto.PatientDto;

import java.util.Optional;
import java.util.Set;

interface PatientService {

    PatientDto findPatientById(long id);

    Set<PatientDto> getAllPatients();

    PatientDto findPatientByPesel(String pesel);

    PatientDto addPatient(PatientDto patientDto);
}
