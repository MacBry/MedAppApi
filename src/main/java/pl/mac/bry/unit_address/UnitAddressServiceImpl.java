package pl.mac.bry.unit_address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.address.StreetPrefix;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UnitAddressServiceImpl implements UnitAddressService {

    private final UnitAddressRepository unitAddressRepository;
    private final UnitAddressDtoMapper unitAddressDtoMapper;

    @Autowired
    public UnitAddressServiceImpl(UnitAddressRepository unitAddressRepository, UnitAddressDtoMapper unitAddressDtoMapper) {
        this.unitAddressRepository = unitAddressRepository;
        this.unitAddressDtoMapper = unitAddressDtoMapper;
    }

    @Override
    public Optional<UnitAddressDto> findUnitAddressById(Long unitAddressId) {
        return unitAddressRepository.findById(unitAddressId)
                .map(unitAddressDtoMapper::map);
    }

    @Override
    public Set<UnitAddressDto> getAllUnitAddresses() {
        return unitAddressRepository.findAll()
                .stream()
                .map(unitAddressDtoMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitAddressDto addUnitAddress(UnitAddressDto unitAddressDto) {
        UnitAddress unitAddress = unitAddressDtoMapper.map(unitAddressDto);
        UnitAddress addedUnitAddress = unitAddressRepository.save(unitAddress);
        return unitAddressDtoMapper.map(addedUnitAddress);
    }

    @Override
    @Transactional
    public Optional<UnitAddressDto> updateUnitAddress(Long unitAddressId, UnitAddressDto unitAddressDto) {
        return unitAddressRepository.findById(unitAddressId)
                .map(target ->setEntityFields(unitAddressDto, target))
                .map(unitAddressDtoMapper::map);
    }

    @Override
    public void deleteUnitAddress(Long unitAddressId) {
        unitAddressRepository.deleteById(unitAddressId);
    }

    private UnitAddress setEntityFields(UnitAddressDto unitAddressDto, UnitAddress target) {
        if(unitAddressDto.getCountry() != null) {
            target.setCountry(unitAddressDto.getCountry());
        }
        if(unitAddressDto.getCity() != null) {
            target.setCity(unitAddressDto.getCity());
        }
        if(unitAddressDto.getStreetPrefix() != null) {
            target.setStreetPrefix(StreetPrefix.valuesOfDescription(unitAddressDto.getStreetPrefix()));
        }
        if(unitAddressDto.getStreet() != null) {
            target.setStreet(unitAddressDto.getStreet());
        }
        if(unitAddressDto.getZipCode() != null) {
            target.setZipCode(unitAddressDto.getZipCode());
        }
        if(unitAddressDto.getBuildingNumber() != null) {
            target.setBuildingNumber(unitAddressDto.getBuildingNumber());
        }
        if(unitAddressDto.getApartmentNumber() != null) {
            target.setApartmentNumber(unitAddressDto.getApartmentNumber());
        }
        return target;
    }


}
