package pl.mac.bry.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.patient.dto.PatientDto;
import pl.mac.bry.patient.exceptions.InvalidPatientIdException;

@Service
public class PatientFacade {
    private final PatientRepository repository;
    private final PatientDtoMapper dtoMapper;

    @Autowired
    public PatientFacade(PatientRepository repository
            , PatientDtoMapper dtoMapper) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
    }

    public Patient getPatient(long patientId) {
        return repository.findById(patientId)
                .orElseThrow(() -> new InvalidPatientIdException(patientId));
    }
}
