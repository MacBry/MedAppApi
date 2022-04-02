package pl.mac.bry.address;

import java.util.Optional;
import java.util.Set;

interface AddressService {

    Optional<SimpleAddressDto> findAddressById(Long addressId);

    Set<SimpleAddressDto> getAllAddresses();

    Set<ComplexAddressDto> getAllPatientAddresses(Long patientId);

    SimpleAddressDto addAddress(SimpleAddressDto simpleAddressDto);

    Optional<SimpleAddressDto> updateAddress(Long addressId, SimpleAddressDto simpleAddressDto);

    void deleteAddress(Long addressId);
}
