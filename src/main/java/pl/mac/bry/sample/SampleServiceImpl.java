package pl.mac.bry.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.mac.bry.sample.SampleDtoMapper.DATE_FORMAT;

@Service
public class SampleServiceImpl implements SampleService {

    private SampleRepository sampleRepository;
    private SampleDtoMapper sampleDtoMapper;

    @Autowired
    public SampleServiceImpl(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public Optional<SampleDto> findSampleById(Long sampleId) {
        return sampleRepository.findById(sampleId)
                .map(sampleDtoMapper::map);
    }

    @Override
    public Set<SampleDto> getAllSamples() {
        return sampleRepository.findAll()
                .stream()
                .map(sampleDtoMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public SampleDto addSample(SampleDto sampleDto) {
        Sample sample = sampleDtoMapper.map(sampleDto);
        Sample addedSample = sampleRepository.save(sample);
        return sampleDtoMapper.map(addedSample);
    }

    @Override
    public Optional<SampleDto> updateSample(Long sampleId, SampleDto sampleDto) {
        return sampleRepository.findById(sampleId)
                .map(target -> setEntityFields(sampleDto, target))
                .map(sampleDtoMapper::map);
    }

    @Override
    public void deleteSample(Long sampleId) {
        sampleRepository.deleteById(sampleId);
    }

    private Sample setEntityFields(SampleDto sampleDto, Sample target) {
        if(sampleDto.getDonationDateTime() != null) {
            target.setDonationDateTime(getZonedDateTimeFromString(sampleDto.getDonationDateTime()));
        }
        if(sampleDto.getSampleType() != null) {
            target.setSampleType(SampleType.valuesOfDescription(sampleDto.getSampleType()));
        }
        return target;
    }

    private ZonedDateTime getZonedDateTimeFromString(String donationDateTime) {
        return ZonedDateTime.parse(donationDateTime, getDateTimeFormatter());
    }

    private DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }
}
