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

    @GetMapping("/id/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") long id) {
        return patientService.findPatientById(id)
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
                .path("/{id}")
                .buildAndExpand(addedPatient.getId())
                .toUri();
        return ResponseEntity.created(addedPatientUri).body(addedPatient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable("id") long id, @RequestBody PatientDto patientDto) {
        return patientService.updatePatient(id,patientDto)
                .map(patient->ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
