package pl.mac.bry.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.patient.PatientFacade;
import pl.mac.bry.sample.dto.ComplexSampleDto;
import pl.mac.bry.sample.dto.SimpleSampleDto;
import pl.mac.bry.sample.enums.SampleType;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class SampleServiceImpl implements SampleService{

    private final SampleRepository sampleRepository;
    private final SampleDtoMapper sampleDtoMapper;
    private final PatientFacade patientFacade;


    @Autowired
    SampleServiceImpl(SampleRepository sampleRepository
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
        Sample sampleToSave = sampleDtoMapper.simpleMapping(simpleSampleDto);
        Patient patient = patientFacade.getPatient(sampleToSave.getPatient().getId());
        sampleToSave.setRegistrationDateTime(ZonedDateTime.now());
        sampleToSave.setPatient(patient);
        Sample savedSample = sampleRepository.save(sampleToSave);
        return  sampleDtoMapper.simpleMapping(savedSample);
    }

    @Override
    public Optional<SimpleSampleDto> updateSample(Long sampleId, SimpleSampleDto simpleSampleDto) {
        return sampleRepository.findById(sampleId)
                .map(target -> setEntityFields(simpleSampleDto, target))
                .map(sampleDtoMapper::simpleMapping);
    }

    @Override
    public void deleteSample(Long sampleId) {
        sampleRepository.deleteById(sampleId);
    }

    private Sample setEntityFields(SimpleSampleDto simpleSampleDto, Sample target) {
        if(simpleSampleDto.getDonationDateTime() != null) {
            target.setDonationDateTime(getZonedDateTimeFromString(simpleSampleDto.getDonationDateTime()));
        }
        if(simpleSampleDto.getSampleType() != null) {
            target.setSampleType(SampleType.valuesOfDescription(simpleSampleDto.getSampleType()));
        }
        return  target;
    }

    private ZonedDateTime getZonedDateTimeFromString(String donationDateTime) {
        return ZonedDateTime.parse(donationDateTime, getDateTimeFormatter());
    }

    private DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(SampleDtoMapper.DATE_FORMAT);
    }


}
