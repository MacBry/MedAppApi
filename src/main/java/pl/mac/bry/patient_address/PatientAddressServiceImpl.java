package pl.mac.bry.patient_address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.address.StreetPrefix;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.patient.PatientFacade;
import pl.mac.bry.exceptions.InvalidPatientIdException;


import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class PatientAddressServiceImpl implements PatientAddressService {

    private final PatientAddressRepository patientAddressRepository;
    private final PatientAddressDtoMapper patientAddressDtoMapper;
    private final PatientFacade patientFacade;

    @Autowired
    public PatientAddressServiceImpl(PatientAddressRepository patientAddressRepository,
                                     PatientAddressDtoMapper patientAddressDtoMapper,
                                     PatientFacade patientFacade) {
        this.patientAddressRepository = patientAddressRepository;
        this.patientAddressDtoMapper = patientAddressDtoMapper;
        this.patientFacade = patientFacade;
    }

    @Override
    public Optional<SimplePatientAddressDto> findAddressById(Long addressId) {
        return patientAddressRepository.findById(addressId)
                .map(patientAddressDtoMapper::simpleMapping);
    }

    @Override
    public Set<SimplePatientAddressDto> getAllAddresses() {
        return patientAddressRepository.findAll()
                .stream()
                .map(patientAddressDtoMapper::simpleMapping)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ComplexPatientAddressDto> getAllPatientAddresses(Long patientId) {
        Patient patient = patientFacade.getPatient(patientId)
                .orElseThrow(() -> new InvalidPatientIdException(patientId));
        return patient.getPatientAddresses()
                .stream()
                .map(patientAddressDtoMapper::complexMapping)
                .collect(Collectors.toSet());

    }

    @Override
    public SimplePatientAddressDto addAddress(SimplePatientAddressDto simplePatientAddressDto) {
        PatientAddress patientAddressToSave = patientAddressDtoMapper.simpleMapping(simplePatientAddressDto);
        PatientAddress savedPatientAddress = patientAddressRepository.save(patientAddressToSave);
        return patientAddressDtoMapper.simpleMapping(savedPatientAddress);
    }

    @Override
    @Transactional
    public Optional<SimplePatientAddressDto> updateAddress(Long addressId, SimplePatientAddressDto simplePatientAddressDto) {
        return patientAddressRepository.findById(addressId)
                .map(target -> setEntityFields(simplePatientAddressDto, target))
                .map(patientAddressDtoMapper::simpleMapping);
    }

    @Override
    public void deleteAddress(Long addressId) {
        patientAddressRepository.deleteById(addressId);
    }

    private PatientAddress setEntityFields(SimplePatientAddressDto simplePatientAddressDto, PatientAddress target) {
        if(simplePatientAddressDto.getCountry() != null) {
            target.setCountry(simplePatientAddressDto.getCountry());
        }
        if(simplePatientAddressDto.getCity() != null) {
            target.setCity(simplePatientAddressDto.getCity());
        }
        if(simplePatientAddressDto.getStreetPrefix() != null) {
            target.setStreetPrefix(StreetPrefix.valuesOfDescription(simplePatientAddressDto.getStreetPrefix()));
        }
        if(simplePatientAddressDto.getStreet() != null) {
            target.setStreet(simplePatientAddressDto.getStreet());
        }
        if(simplePatientAddressDto.getZipCode() != null) {
            target.setZipCode(simplePatientAddressDto.getZipCode());
        }
        if(simplePatientAddressDto.getBuildingNumber() != null) {
            target.setBuildingNumber(simplePatientAddressDto.getBuildingNumber());
        }
        if(simplePatientAddressDto.getApartmentNumber() != null) {
            target.setApartmentNumber(simplePatientAddressDto.getApartmentNumber());
        }
        return target;
    }


}
