package pl.mac.bry.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mac.bry.patient.Patient;

@Repository
interface PatientRepository extends JpaRepository<Patient, Long> {
}
