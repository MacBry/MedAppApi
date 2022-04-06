package pl.mac.bry.referral_unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReferralUnitFacade {

    private final ReferralUnitRepository repository;

    @Autowired
    public ReferralUnitFacade(ReferralUnitRepository repository) {
        this.repository = repository;
    }

    public Optional<ReferralUnit> getReferralUnit(long unitId) {
        return repository.findById(unitId);
    }
}
