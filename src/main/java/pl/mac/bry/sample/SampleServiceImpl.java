package pl.mac.bry.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.patient.PatientFacade;
import pl.mac.bry.sample.dto.ComplexSampleDto;
import pl.mac.bry.sample.dto.SimpleSampleDto;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SampleServiceImpl implements SampleService{

    private final SampleRepository sampleRepository;
    private final SampleDtoMapper sampleDtoMapper;
    private final PatientFacade patientFacade;

    @Autowired
    public SampleServiceImpl(SampleRepository sampleRepository
            , SampleDtoMapper sampleDtoMapper
            , PatientFacade patientFacade) {
        this.sampleRepository = sampleRepository;
        this.sampleDtoMapper = sampleDtoMapper;
        this.patientFacade = patientFacade;
    }

    @Override
    public Optional<SimpleSampleDto> findSampleById(Long sampleId) {
        return sampleRepository.findById(sampleId)
                .map(sampleDtoMapper::simpleMapping);
    }

    @Override
    public Set<SimpleSampleDto> getAllSamples() {
        return sampleRepository.findAll()
                .stream()
                .map(sampleDtoMapper::simpleMapping)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ComplexSampleDto> getPatientAllSamples(Long patientId) {
        Patient patient = patientFacade.getPatient(patientId);
        return patient.getPatientSamples()
                .stream()
                .map(sampleDtoMapper::complexMapping)
                .collect(Collectors.toSet());
    }

    @Override
    public SimpleSampleDto addSample(SimpleSampleDto simpleSampleDto) {
        return null;
    }

    @Override
    public Optional<SimpleSampleDto> updateSample(Long sampleId, SimpleSampleDto simpleSampleDto) {
        return Optional.empty();
    }

    @Override
    public void deleteSample(Long sampleId) {

    }
}
