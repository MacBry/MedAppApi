package pl.mac.bry.lab_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/lab_tests")
public class LabTestController {

    private final LabTestService labTestService;

    @Autowired
    public LabTestController(LabTestService labTestService) {
        this.labTestService = labTestService;
    }

    @GetMapping
    public ResponseEntity<Set<LabTestDto>> getAllTests () {
        if(labTestService.getAllLabTest().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(labTestService.getAllLabTest());
    }

    @GetMapping("/id/{testId}")
    public ResponseEntity<LabTestDto> getTestById (@PathVariable("testId") long testId) {
        return labTestService.findLabTestById(testId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LabTestDto> saveTest(@RequestBody LabTestDto labTestDto) {
        LabTestDto addedLabTest = labTestService.addLabTest(labTestDto);
        URI addedLabTestUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{testId}")
                .buildAndExpand(addedLabTest.getId())
                .toUri();
        return ResponseEntity.created(addedLabTestUri).body(addedLabTest);
    }

    @PatchMapping("/{testId}")
    public ResponseEntity<?> updateTest(@PathVariable("testId") long testId, @RequestBody LabTestDto labTestDto) {
        return labTestService.updateLabTest(testId, labTestDto)
                .map(tests -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteTest(@PathVariable("testID") long testId) {
        labTestService.deleteLabTest(testId);
        return ResponseEntity.noContent().build();
    }

}
