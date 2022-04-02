package pl.mac.bry.patient;

import java.util.Optional;
import java.util.Set;

interface PatientService {

    Optional<PatientDto> findPatientById(Long id);

    Set<PatientDto> getAllPatients();

    Optional<PatientDto> findPatientByPesel(String pesel);

    PatientDto addPatient(PatientDto patientDto);

    Optional<PatientDto> updatePatient(Long patientId, PatientDto patientDto);

    void deletePatient(Long id);
}
