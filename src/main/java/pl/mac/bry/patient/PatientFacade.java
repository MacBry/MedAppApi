package pl.mac.bry.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<Patient> getPatient(long patientId) {
        return repository.findById(patientId);
    }

    public PatientDto map(Patient patient) {
        return dtoMapper.map(patient);
    }
    public Patient map (PatientDto dto) {
        return dtoMapper.map(dto);
    }
}
