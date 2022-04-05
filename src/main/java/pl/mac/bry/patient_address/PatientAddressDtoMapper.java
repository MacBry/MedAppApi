package pl.mac.bry.patient_address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.address.StreetPrefix;
import pl.mac.bry.patient.PatientFacade;

@Service
class PatientAddressDtoMapper {

    private final PatientFacade patientFacade;

    @Autowired
    public PatientAddressDtoMapper(PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

    SimplePatientAddressDto simpleMapping(PatientAddress patientAddress) {
        return SimplePatientAddressDto.builder()
                .id(patientAddress.getId())
                .country(patientAddress.getCountry())
                .city(patientAddress.getCity())
                .streetPrefix(patientAddress.getStreetPrefix().getDescription())
                .street(patientAddress.getStreet())
                .zipCode(patientAddress.getZipCode())
                .buildingNumber(patientAddress.getBuildingNumber())
                .apartmentNumber(patientAddress.getApartmentNumber())
                .patientId((patientAddress.getPatient().getId()))
                .build();
    }

    PatientAddress simpleMapping(SimplePatientAddressDto dto) {
        PatientAddress patientAddress = PatientAddress.builder()
                .country(dto.getCountry())
                .city(dto.getCity())
                .streetPrefix(StreetPrefix.valuesOfDescription(dto.getStreetPrefix()))
                .street(dto.getStreet())
                .zipCode(dto.getZipCode())
                .buildingNumber(dto.getBuildingNumber())
                .apartmentNumber(dto.getApartmentNumber())
                .build();
        patientFacade.getPatient(dto.getPatientId())
                .ifPresent(patientAddress::setPatient);
        return patientAddress;
    }

    ComplexPatientAddressDto complexMapping(PatientAddress patientAddress) {
        return ComplexPatientAddressDto.builder()
                .id(patientAddress.getId())
                .country(patientAddress.getCountry())
                .city(patientAddress.getCity())
                .streetPrefix(patientAddress.getStreetPrefix().getDescription())
                .street(patientAddress.getStreet())
                .zipCode(patientAddress.getZipCode())
                .buildingNumber(patientAddress.getBuildingNumber())
                .apartmentNumber(patientAddress.getApartmentNumber())
                .firstName(patientAddress.getPatient().getFirstName())
                .lastName(patientAddress.getPatient().getLastName())
                .build();

    }

    PatientAddress complexMapping(ComplexPatientAddressDto dto) {
        return PatientAddress.builder()
                .country(dto.getCountry())
                .city(dto.getCity())
                .streetPrefix(StreetPrefix.valuesOfDescription(dto.getStreetPrefix()))
                .street(dto.getStreet())
                .zipCode(dto.getZipCode())
                .buildingNumber(dto.getBuildingNumber())
                .apartmentNumber(dto.getApartmentNumber())
                .build();
    }
}
