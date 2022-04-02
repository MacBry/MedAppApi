package pl.mac.bry.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.patient.PatientFacade;
import pl.mac.bry.exceptions.InvalidPatientIdException;


import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressDtoMapper addressDtoMapper;
    private final PatientFacade patientFacade;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,
                              AddressDtoMapper addressDtoMapper,
                              PatientFacade patientFacade) {
        this.addressRepository = addressRepository;
        this.addressDtoMapper = addressDtoMapper;
        this.patientFacade = patientFacade;
    }

    @Override
    public Optional<SimpleAddressDto> findAddressById(Long addressId) {
        return addressRepository.findById(addressId)
                .map(addressDtoMapper::simpleMapping);
    }

    @Override
    public Set<SimpleAddressDto> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(addressDtoMapper::simpleMapping)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ComplexAddressDto> getAllPatientAddresses(Long patientId) {
        Patient patient = patientFacade.getPatient(patientId)
                .orElseThrow(() -> new InvalidPatientIdException(patientId));
        return patient.getAddresses()
                .stream()
                .map(addressDtoMapper::complexMapping)
                .collect(Collectors.toSet());

    }

    @Override
    public SimpleAddressDto addAddress(SimpleAddressDto simpleAddressDto) {
        Address addressToSave = addressDtoMapper.simpleMapping(simpleAddressDto);
        Address savedAddress = addressRepository.save(addressToSave);
        return addressDtoMapper.simpleMapping(savedAddress);
    }

    @Override
    @Transactional
    public Optional<SimpleAddressDto> updateAddress(Long addressId, SimpleAddressDto simpleAddressDto) {
        return addressRepository.findById(addressId)
                .map(target -> setEntityFields(simpleAddressDto, target))
                .map(addressDtoMapper::simpleMapping);
    }

    @Override
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    private Address setEntityFields(SimpleAddressDto simpleAddressDto, Address target) {
        if(simpleAddressDto.getCountry() != null) {
            target.setCountry(simpleAddressDto.getCountry());
        }
        if(simpleAddressDto.getCity() != null) {
            target.setCity(simpleAddressDto.getCity());
        }
        if(simpleAddressDto.getStreetPrefix() != null) {
            target.setStreetPrefix(StreetPrefix.valuesOfDescription(simpleAddressDto.getStreetPrefix()));
        }
        if(simpleAddressDto.getStreet() != null) {
            target.setStreet(simpleAddressDto.getStreet());
        }
        if(simpleAddressDto.getZipCode() != null) {
            target.setZipCode(simpleAddressDto.getZipCode());
        }
        if(simpleAddressDto.getBuildingNumber() != null) {
            target.setBuildingNumber(simpleAddressDto.getBuildingNumber());
        }
        if(simpleAddressDto.getApartmentNumber() != null) {
            target.setApartmentNumber(simpleAddressDto.getApartmentNumber());
        }
        return target;
    }


}
