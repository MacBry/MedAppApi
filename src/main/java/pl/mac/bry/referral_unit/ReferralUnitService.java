package pl.mac.bry.referral_unit;


import java.util.Optional;
import java.util.Set;

interface ReferralUnitService {

    Optional<ReferralUnitDto> findUnitById (Long unitId);

    Set<ReferralUnitDto> getAllUnits ();

    ReferralUnitDto addUnit(ReferralUnitDto referralUnitDto);

    Optional<ReferralUnitDto> updateUnit(Long unitId, ReferralUnitDto referralUnitDto);

    void deleteUnit(Long unitId);
}
