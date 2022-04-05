package pl.mac.bry.patient_address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PatientAddressRepository extends JpaRepository<PatientAddress, Long> {
}
