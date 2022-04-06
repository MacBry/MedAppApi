package pl.mac.bry.unit_address;

import java.util.Optional;
import java.util.Set;

public interface UnitAddressService {

    Optional<UnitAddressDto> findUnitAddressById (Long unitAddressId);

    Set<UnitAddressDto> getAllUnitAddresses ();

    UnitAddressDto addUnitAddress(UnitAddressDto unitAddressDto);

    Optional<UnitAddressDto> updateUnitAddress (Long unitAddressId, UnitAddressDto unitAddressDto);

    void deleteUnitAddress(Long unitAddressId);
}
