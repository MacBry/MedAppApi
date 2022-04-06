package pl.mac.bry.referral_unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ReferralUnitRepository extends JpaRepository<ReferralUnit, Long> {
}
