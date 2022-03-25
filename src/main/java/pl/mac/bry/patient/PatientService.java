package pl.mac.bry.patient;

import pl.mac.bry.patient.dto.PatientDto;

import java.util.Optional;
import java.util.Set;

interface PatientService {

    Optional<PatientDto> findPatientById(long id);

    Set<PatientDto> getAllPatients();

    Optional<PatientDto> findPatientByPesel(String pesel);

    PatientDto addPatient(PatientDto patientDto);

    Optional<PatientDto> updatePatient(Long patientId, PatientDto patientDto);

    void deletePatient(Long id);
}
