package pl.mac.bry.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.mac.bry.patient.dto.PatientDto;
import pl.mac.bry.patient.exceptions.InvalidPatientIdException;
import pl.mac.bry.patient.exceptions.InvalidPatientPeselException;

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
        PatientDto patientDto;
        try {
            patientDto = patientService.findPatientById(id);
        }
        catch (InvalidPatientIdException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientDto);
    }
    @GetMapping("/pesel/{pesel}")
    public ResponseEntity<PatientDto> getPatientByPesel(@PathVariable("pesel") String pesel) {
        PatientDto patientDto;
        try {
            patientDto = patientService.findPatientByPesel(pesel);
        }
        catch (InvalidPatientPeselException ex) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patientDto);
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
}
