package pl.mac.bry.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<Set<SimpleAddressDto>> getAllAddresses () {
        if(addressService.getAllAddresses().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/id/{addressId}")
    public ResponseEntity<SimpleAddressDto> getAddressById(@PathVariable("addressId") long addressId) {
        return addressService.findAddressById(addressId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}/addresses")
    public ResponseEntity<Set<ComplexAddressDto>> getPatientAllAddresses(@PathVariable("patientId") long patientId) {
        if(addressService.getAllPatientAddresses(patientId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressService.getAllPatientAddresses(patientId));
    }

    @PostMapping
    public ResponseEntity<SimpleAddressDto> saveAddress (@RequestBody SimpleAddressDto simpleAddressDto) {
        SimpleAddressDto addedAddress = addressService.addAddress(simpleAddressDto);
        URI addedAddressUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{addressId}")
                .buildAndExpand(addedAddress.getId())
                .toUri();
        return ResponseEntity.created(addedAddressUri).body(addedAddress);
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(@PathVariable("addressId") long addressId, @RequestBody SimpleAddressDto simpleAddressDto) {
        return addressService.updateAddress(addressId, simpleAddressDto)
                .map(address -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }
}
