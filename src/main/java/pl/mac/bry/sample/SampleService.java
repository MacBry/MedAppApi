package pl.mac.bry.sample;

import pl.mac.bry.sample.dto.ComplexSampleDto;
import pl.mac.bry.sample.dto.SimpleSampleDto;

import java.util.Optional;
import java.util.Set;

interface SampleService {

    Optional<SimpleSampleDto> findSampleById(Long sampleId);

    Set<SimpleSampleDto> getAllSamples();

    Set<ComplexSampleDto> getPatientAllSamples(Long patientId);

    SimpleSampleDto addSample(SimpleSampleDto simpleSampleDto);

    Optional<SimpleSampleDto> updateSample(Long sampleId, SimpleSampleDto simpleSampleDto);

    void deleteSample(Long sampleId);



}
