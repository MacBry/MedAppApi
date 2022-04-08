package pl.mac.bry.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<Set<SampleDto>> getAllSamples() {
        if(sampleService.getAllSamples().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sampleService.getAllSamples());
    }

    @GetMapping("/{sampleId}")
    public ResponseEntity<SampleDto> getSampleById(@PathVariable("sampleId") long sampleId) {
        return sampleService.findSampleById(sampleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SampleDto> saveSample(@RequestBody SampleDto sampleDto) {
        SampleDto addedSample = sampleService.addSample(sampleDto);
        URI addedSampleUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{sampleId}")
                .buildAndExpand(addedSample.getId())
                .toUri();
        return ResponseEntity.created(addedSampleUri).body(addedSample);
    }

    @PatchMapping("/{sampleId}")
    public ResponseEntity<?> updateSample(@PathVariable("sampleId") long sampleId,
                @RequestBody SampleDto sampleDto) {
        return sampleService.updateSample(sampleId, sampleDto)
                .map(sample ->ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{sampleId}")
    public ResponseEntity<?> deleteSample(@PathVariable("sampleId")long sampleId) {
        sampleService.deleteSample(sampleId);
        return ResponseEntity.noContent().build();
    }
}
