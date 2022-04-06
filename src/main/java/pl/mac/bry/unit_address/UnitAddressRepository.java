package pl.mac.bry.unit_address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitAddressRepository extends JpaRepository<UnitAddress, Long> {
}
