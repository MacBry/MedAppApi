package pl.mac.bry.sample;

import java.util.Optional;
import java.util.Set;

interface SampleService {

    Optional<SampleDto> findSampleById(Long sampleId);

    Set<SampleDto> getAllSamples();

    SampleDto addSample(SampleDto sampleDto);

    Optional<SampleDto> updateSample(Long sampleId, SampleDto sampleDto);

    void deleteSample(Long sampleId);



}
