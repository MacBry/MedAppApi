package pl.mac.bry.referral_unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class ReferralUnitServiceImpl implements ReferralUnitService{

    private final ReferralUnitRepository unitRepository;
    private final ReferralUnitDtoMapper unitDtoMapper;

    @Autowired
    public ReferralUnitServiceImpl(ReferralUnitRepository unitRepository, ReferralUnitDtoMapper unitDtoMapper) {
        this.unitRepository = unitRepository;
        this.unitDtoMapper = unitDtoMapper;
    }

    @Override
    public Optional<ReferralUnitDto> findUnitById(Long unitId) {
        return unitRepository.findById(unitId).map(unitDtoMapper::map);
    }

    @Override
    public Set<ReferralUnitDto> getAllUnits() {
        return unitRepository.findAll()
                .stream()
                .map(unitDtoMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public ReferralUnitDto addUnit(ReferralUnitDto referralUnitDto) {
        ReferralUnit referralUnit = unitDtoMapper.map(referralUnitDto);
        ReferralUnit addedUnit = unitRepository.save(referralUnit);
        return unitDtoMapper.map(addedUnit);
    }

    @Override
    @Transactional
    public Optional<ReferralUnitDto> updateUnit(Long unitId, ReferralUnitDto referralUnitDto) {
        return unitRepository.findById(unitId)
                .map(target -> setEntityFields(referralUnitDto,target))
                .map(unitDtoMapper::map);
    }

    @Override
    public void deleteUnit(Long unitId) {
        unitRepository.deleteById(unitId);
    }

    private ReferralUnit setEntityFields(ReferralUnitDto referralUnitDto, ReferralUnit target) {
        if(referralUnitDto.getFullName() != null) {
            target.setFullName(referralUnitDto.getFullName());
        }
        if(referralUnitDto.getShortName() != null) {
            target.setShortName(referralUnitDto.getShortName());
        }
        if(referralUnitDto.getNipNumber() != null) {
            target.setNipNumber(Long.parseLong(referralUnitDto.getNipNumber()));
        }
        if(referralUnitDto.getRegonNumber() != null) {
            target.setRegonNumber(Long.parseLong(referralUnitDto.getRegonNumber()));
        }
        if(referralUnitDto.getEmail() != null) {
            target.setEmail(referralUnitDto.getEmail());
        }
        if(referralUnitDto.getResortBookNumber() != null) {
            target.setResortBookNumber(referralUnitDto.getResortBookNumber());
        }
        return target;
    }


}
