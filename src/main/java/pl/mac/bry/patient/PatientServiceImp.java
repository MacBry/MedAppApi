package pl.mac.bry.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.patient.dto.PatientDto;
import pl.mac.bry.patient.exceptions.InvalidPatientIdException;
import pl.mac.bry.patient.exceptions.InvalidPatientPeselException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientDtoMapper patientDtoMapper;

    @Autowired
    public PatientServiceImp(PatientRepository patientRepository, PatientDtoMapper patientDtoMapper) {
        this.patientRepository = patientRepository;
        this.patientDtoMapper = patientDtoMapper;
    }

    @Override
    public PatientDto findPatientById(long id) {
        return patientRepository.findById(id).map(patientDtoMapper::map)
                .orElseThrow(() -> new InvalidPatientIdException(id));
    }

    @Override
    public Set<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream().map(patientDtoMapper::map).collect(Collectors.toSet());
    }

    @Override
    public PatientDto findPatientByPesel(String pesel) {
        return patientRepository.findPatientByPesel(pesel).map(patientDtoMapper::map)
                .orElseThrow(() -> new InvalidPatientPeselException(pesel));
    }

    @Override
    public PatientDto addPatient(PatientDto patientDto) {
        Patient patient = patientDtoMapper.map(patientDto);
        Patient addedPatient = patientRepository.save(patient);
        return patientDtoMapper.map(addedPatient);
    }
}
