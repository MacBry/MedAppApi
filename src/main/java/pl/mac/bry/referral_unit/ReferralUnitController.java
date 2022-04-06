package pl.mac.bry.referral_unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/units")
public class ReferralUnitController {

    private final ReferralUnitService unitService;

    @Autowired
    public ReferralUnitController(ReferralUnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public ResponseEntity<Set<ReferralUnitDto>> getAllUnits() {
        if(unitService.getAllUnits().isEmpty()) {
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(unitService.getAllUnits());
    }
    @GetMapping("/id/{unitId}")
    public ResponseEntity<ReferralUnitDto> getUnitById(@PathVariable("unitId") long unitId) {
        return unitService.findUnitById(unitId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReferralUnitDto> saveUnit(@RequestBody ReferralUnitDto referralUnitDto) {
        ReferralUnitDto addedUnit = unitService.addUnit(referralUnitDto);
        URI addedUnitUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{unitId}")
                .buildAndExpand(addedUnit.getId())
                .toUri();
        return ResponseEntity.created(addedUnitUri).body(addedUnit);
    }

    @PatchMapping("/{unitId}")
    public ResponseEntity<?> updateUnit(@PathVariable("unitId") long unitId, @RequestBody ReferralUnitDto referralUnitDto) {
        return unitService.updateUnit(unitId, referralUnitDto)
                .map(unit -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{unitId}")
    public ResponseEntity<?> deleteUnit(@PathVariable("unitId") long unitId) {
        unitService.deleteUnit(unitId);
        return  ResponseEntity.noContent().build();
    }
}
