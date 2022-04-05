package pl.mac.bry.patient_address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/addresses")
public class PatientAddressController {

    private final PatientAddressService patientAddressService;

    @Autowired
    public PatientAddressController(PatientAddressService patientAddressService) {
        this.patientAddressService = patientAddressService;
    }

    @GetMapping
    public ResponseEntity<Set<SimplePatientAddressDto>> getAllAddresses () {
        if(patientAddressService.getAllAddresses().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(patientAddressService.getAllAddresses());
    }

    @GetMapping("/id/{addressId}")
    public ResponseEntity<SimplePatientAddressDto> getAddressById(@PathVariable("addressId") long addressId) {
        return patientAddressService.findAddressById(addressId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}/addresses")
    public ResponseEntity<Set<ComplexPatientAddressDto>> getPatientAllAddresses(@PathVariable("patientId") long patientId) {
        if(patientAddressService.getAllPatientAddresses(patientId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientAddressService.getAllPatientAddresses(patientId));
    }

    @PostMapping
    public ResponseEntity<SimplePatientAddressDto> saveAddress (@RequestBody SimplePatientAddressDto simplePatientAddressDto) {
        SimplePatientAddressDto addedAddress = patientAddressService.addAddress(simplePatientAddressDto);
        URI addedAddressUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{addressId}")
                .buildAndExpand(addedAddress.getId())
                .toUri();
        return ResponseEntity.created(addedAddressUri).body(addedAddress);
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(@PathVariable("addressId") long addressId, @RequestBody SimplePatientAddressDto simplePatientAddressDto) {
        return patientAddressService.updateAddress(addressId, simplePatientAddressDto)
                .map(address -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") long addressId) {
        patientAddressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }
}
