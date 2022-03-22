package pl.mac.bry.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.patient.dto.PatientDto;

import java.util.Optional;

@Repository
interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientByPesel(String pesel);
}
