package pl.mac.bry.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AddressRepository extends JpaRepository<Address, Long> {
}