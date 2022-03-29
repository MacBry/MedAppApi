package pl.mac.bry.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.mac.bry.patient.dto.PatientDto;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/patients")
public class PatientController {

    PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<Set<PatientDto>> getAllPatients () {
        if(patientService.getAllPatients().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/id/{patientId}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("patientId") long patientId) {
        return patientService.findPatientById(patientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/pesel/{pesel}")
    public ResponseEntity<PatientDto> getPatientByPesel(@PathVariable("pesel") String pesel) {
        return patientService.findPatientByPesel(pesel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PatientDto> savePatient(@RequestBody PatientDto patientDto) {
        PatientDto addedPatient = patientService.addPatient(patientDto);
        URI addedPatientUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{patientId}")
                .buildAndExpand(addedPatient.getId())
                .toUri();
        return ResponseEntity.created(addedPatientUri).body(addedPatient);
    }

    @PatchMapping("/{patientId}")
    public ResponseEntity<?> updatePatient(@PathVariable("patientId") long patientId, @RequestBody PatientDto patientDto) {
        return patientService.updatePatient(patientId,patientDto)
                .map(patient->ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable("patientId") long patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build();
    }
}
