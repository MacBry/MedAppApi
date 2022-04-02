package pl.mac.bry.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.mac.bry.sample.dto.ComplexSampleDto;
import pl.mac.bry.sample.dto.SimpleSampleDto;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/samples")
public class SampleController {

    SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping
    public ResponseEntity<Set<SimpleSampleDto>> getAllSamples() {
        if(sampleService.getAllSamples().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sampleService.getAllSamples());
    }

    @GetMapping("/patient/{patientId}/samples")
    public ResponseEntity<Set<ComplexSampleDto>> getPatientAllSamples(@PathVariable("patientId") long patientId) {
        if(sampleService.getPatientAllSamples(patientId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sampleService.getPatientAllSamples(patientId));
    }

    @GetMapping("/{sampleId}")
    public ResponseEntity<SimpleSampleDto> getSampleById(@PathVariable("sampleId") long sampleId) {
        return sampleService.findSampleById(sampleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SimpleSampleDto> saveSample(@RequestBody SimpleSampleDto simpleSampleDto) {
        SimpleSampleDto addedSample = sampleService.addSample(simpleSampleDto);
        URI addedSampleUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{sampleId}")
                .buildAndExpand(addedSample.getId())
                .toUri();
        return ResponseEntity.created(addedSampleUri).body(addedSample);
    }

    @PatchMapping("/{sampleId}")
    public ResponseEntity<?> updateSample(@PathVariable("sampleId") long sampleId,
                @RequestBody SimpleSampleDto simpleSampleDto) {
        return sampleService.updateSample(sampleId, simpleSampleDto)
                .map(sample ->ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{sampleId}")
    public ResponseEntity<?> deleteSample(@PathVariable("sampleId")long sampleId) {
        sampleService.deleteSample(sampleId);
        return ResponseEntity.noContent().build();
    }
}
