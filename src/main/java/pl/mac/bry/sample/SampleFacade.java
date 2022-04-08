package pl.mac.bry.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SampleFacade {

    private final SampleRepository sampleRepository;

    @Autowired
    public SampleFacade(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    public Optional<Sample> getSampleById(Long sampleId) {
        return sampleRepository.findById(sampleId);
    }
}
