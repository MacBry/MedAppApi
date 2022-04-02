package pl.mac.bry.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class PatientServiceImp implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientDtoMapper patientDtoMapper;

    @Autowired
    PatientServiceImp(PatientRepository patientRepository, PatientDtoMapper patientDtoMapper) {
        this.patientRepository = patientRepository;
        this.patientDtoMapper = patientDtoMapper;
    }

    @Override
    public Optional<PatientDto> findPatientById(Long id) {
        return patientRepository.findById(id).map(patientDtoMapper::map);
    }

    @Override
    public Set<PatientDto> getAllPatients() {
        return patientRepository.findAll().stream().map(patientDtoMapper::map).collect(Collectors.toSet());
    }

    @Override
    public Optional<PatientDto> findPatientByPesel(String pesel) {
        return patientRepository.findPatientByPesel(pesel).map(patientDtoMapper::map);
    }

    @Override
    public PatientDto addPatient(PatientDto patientDto) {
        Patient patient = patientDtoMapper.map(patientDto);
        Patient addedPatient = patientRepository.save(patient);
        return patientDtoMapper.map(addedPatient);
    }

    @Override
    @Transactional
    public Optional<PatientDto> updatePatient(Long patientId, PatientDto patientDto) {
        return patientRepository.findById(patientId)
                .map(target -> setEntityFields(patientDto, target))
                .map(patientDtoMapper::map);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    private Patient setEntityFields(PatientDto patientDto, Patient target) {
        if(patientDto.getFirstName() != null) {
            target.setFirstName(patientDto.getFirstName());
        }
        if(patientDto.getLastName() != null) {
            target.setLastName(patientDto.getLastName());
        }
        if(patientDto.getPesel() != null) {
            target.setPesel(patientDto.getPesel());
        }
        return target;
    }
}
