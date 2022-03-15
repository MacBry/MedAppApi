package pl.mac.bry.patient;

import pl.mac.bry.patient.Patient;

import java.util.Optional;
import java.util.Set;

interface PatientService {

    Optional<Patient> findPatientById(long id);

    Set<Optional<Patient>> getAllPatients();
}
