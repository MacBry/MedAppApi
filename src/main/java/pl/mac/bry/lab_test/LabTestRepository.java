package pl.mac.bry.lab_test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LabTestRepository extends JpaRepository<LabTest, Long> {
}
