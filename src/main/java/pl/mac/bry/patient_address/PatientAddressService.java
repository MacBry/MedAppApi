package pl.mac.bry.patient_address;

import java.util.Optional;
import java.util.Set;

interface PatientAddressService {

    Optional<SimplePatientAddressDto> findAddressById(Long addressId);

    Set<SimplePatientAddressDto> getAllAddresses();

    Set<ComplexPatientAddressDto> getAllPatientAddresses(Long patientId);

    SimplePatientAddressDto addAddress(SimplePatientAddressDto simplePatientAddressDto);

    Optional<SimplePatientAddressDto> updateAddress(Long addressId, SimplePatientAddressDto simplePatientAddressDto);

    void deleteAddress(Long addressId);
}
