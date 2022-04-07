package pl.mac.bry.unit_address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/unit_addresses")
public class UnitAddressController {

    private final UnitAddressService unitAddressService;

    @Autowired
    public UnitAddressController(UnitAddressService unitAddressService) {
        this.unitAddressService = unitAddressService;
    }

    @GetMapping
    public ResponseEntity<Set<UnitAddressDto>> getAllUnitsAddresses() {
        if(unitAddressService.getAllUnitAddresses().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(unitAddressService.getAllUnitAddresses());
    }

    @GetMapping("/id/{unitId}")
    public ResponseEntity<UnitAddressDto> getUnitAddressById(@PathVariable ("unitId") long unitId) {
        return unitAddressService.findUnitAddressById(unitId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UnitAddressDto> saveUnitAddress(@RequestBody UnitAddressDto unitAddressDto) {
        UnitAddressDto  addedAddress = unitAddressService.addUnitAddress(unitAddressDto);
        URI addedAddressUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{addressId}")
                .buildAndExpand(addedAddress.getId())
                .toUri();
        return ResponseEntity.created(addedAddressUri).body(addedAddress);
    }

    @PatchMapping("/{unitId}")
    public ResponseEntity<?> updateUnitAddress(@PathVariable("unitId") long unitId, @RequestBody UnitAddressDto unitAddressDto) {
        return unitAddressService.updateUnitAddress(unitId, unitAddressDto)
                .map(address -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{unitId}")
    public ResponseEntity<?> deleteUnitAddress(@PathVariable("unitId") long unitId) {
        unitAddressService.deleteUnitAddress(unitId);
        return ResponseEntity.noContent().build();
    }
}
