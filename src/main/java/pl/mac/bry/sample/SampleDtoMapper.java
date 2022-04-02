package pl.mac.bry.sample;

import org.springframework.stereotype.Service;
import pl.mac.bry.sample.dto.ComplexSampleDto;
import pl.mac.bry.sample.dto.SimpleSampleDto;
import pl.mac.bry.sample.enums.SampleType;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
class SampleDtoMapper {

    public static final String DATE_FORMAT = "MM/dd/yyyy - HH:mm:ss Z";


    SimpleSampleDto simpleMapping (Sample sample) {
        return SimpleSampleDto.builder()
                .id(sample.getId())
                .donationDateTime(getStringFromZonedDateTime(sample.getDonationDateTime()))
                .registrationDateTime(getStringFromZonedDateTime(sample.getRegistrationDateTime()))
                .sampleType(sample.getSampleType().getDescription())
                .build();

    }

    Sample simpleMapping (SimpleSampleDto dto) {
        return Sample.builder()
                .donationDateTime(getZonedDateTimeFromString(dto.getDonationDateTime()))
                .sampleType(SampleType.valuesOfDescription(dto.getSampleType()))
                .build();
    }

    ComplexSampleDto complexMapping (Sample sample) {
        return ComplexSampleDto.builder()
                .id(sample.getId())
                .donationDateTime(getStringFromZonedDateTime(sample.getDonationDateTime()))
                .registrationDateTime(getStringFromZonedDateTime(sample.getRegistrationDateTime()))
                .sampleType(sample.getSampleType().getDescription())
                .build();
    }

    Sample complexMapping (ComplexSampleDto dto) {
        return Sample.builder()
                .donationDateTime(getZonedDateTimeFromString(dto.getDonationDateTime()))
                .sampleType(SampleType.valuesOfDescription(dto.getSampleType()))
                .build();
    }

    private ZonedDateTime getZonedDateTimeFromString(String donationDateTime) {
        return ZonedDateTime.parse(donationDateTime, getDateTimeFormatter());
    }

    private String getStringFromZonedDateTime(ZonedDateTime dateTime) {
        DateTimeFormatter formatter = getDateTimeFormatter();
        return dateTime.format(formatter);
    }

    private DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }
}
