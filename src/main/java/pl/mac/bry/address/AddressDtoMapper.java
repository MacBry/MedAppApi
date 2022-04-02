package pl.mac.bry.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.patient.PatientFacade;

@Service
class AddressDtoMapper {

    private final PatientFacade patientFacade;

    @Autowired
    public AddressDtoMapper(PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

    SimpleAddressDto simpleMapping(Address address) {
        return SimpleAddressDto.builder()
                .id(address.getId())
                .country(address.getCountry())
                .city(address.getCity())
                .streetPrefix(address.getStreetPrefix().getDescription())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .buildingNumber(address.getBuildingNumber())
                .apartmentNumber(address.getApartmentNumber())
                .patientId((address.getPatient().getId()))
                .build();
    }

    Address simpleMapping(SimpleAddressDto dto) {
        Address address= Address.builder()
                .country(dto.getCountry())
                .city(dto.getCity())
                .streetPrefix(StreetPrefix.valuesOfDescription(dto.getStreetPrefix()))
                .street(dto.getStreet())
                .zipCode(dto.getZipCode())
                .buildingNumber(dto.getBuildingNumber())
                .apartmentNumber(dto.getApartmentNumber())
                .build();
        patientFacade.getPatient(dto.getPatientId())
                .ifPresent(address::setPatient);
        return address;
    }

    ComplexAddressDto complexMapping(Address address) {
        return ComplexAddressDto.builder()
                .id(address.getId())
                .country(address.getCountry())
                .city(address.getCity())
                .streetPrefix(address.getStreetPrefix().getDescription())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .buildingNumber(address.getBuildingNumber())
                .apartmentNumber(address.getApartmentNumber())
                .firstName(address.getPatient().getFirstName())
                .lastName(address.getPatient().getLastName())
                .build();

    }

    Address complexMapping(ComplexAddressDto dto) {
        return Address.builder()
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
