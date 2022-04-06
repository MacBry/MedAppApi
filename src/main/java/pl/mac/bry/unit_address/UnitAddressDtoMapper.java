package pl.mac.bry.unit_address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.address.StreetPrefix;
import pl.mac.bry.referral_unit.ReferralUnitFacade;

@Service
public class UnitAddressDtoMapper {

    private final ReferralUnitFacade unitFacade;

    @Autowired
    public UnitAddressDtoMapper(ReferralUnitFacade unitFacade) {
        this.unitFacade = unitFacade;
    }

    public UnitAddressDto map (UnitAddress unitAddress) {
        return UnitAddressDto.builder()
                .id(unitAddress.getId())
                .country(unitAddress.getCountry())
                .city(unitAddress.getCity())
                .streetPrefix(unitAddress.getStreetPrefix().getDescription())
                .street(unitAddress.getStreet())
                .zipCode(unitAddress.getZipCode())
                .buildingNumber(unitAddress.getBuildingNumber())
                .apartmentNumber(unitAddress.getApartmentNumber())
                .unitId((unitAddress.getReferralUnit().getId()))
                .build();
    }

    public UnitAddress map (UnitAddressDto dto) {
        UnitAddress unitAddress = UnitAddress.builder()
                .country(dto.getCountry())
                .city(dto.getCity())
                .streetPrefix(StreetPrefix.valuesOfDescription(dto.getStreetPrefix()))
                .street(dto.getStreet())
                .zipCode(dto.getZipCode())
                .buildingNumber(dto.getBuildingNumber())
                .apartmentNumber(dto.getApartmentNumber())
                .build();
        unitFacade.getReferralUnit(dto.getUnitId())
                .ifPresent(unitAddress::setReferralUnit);
        return unitAddress;
    }
}
