package pl.mac.bry.sample;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
class SampleDtoMapper {

    public static final String DATE_FORMAT = "MM/dd/yyyy - HH:mm:ss Z";


    SampleDto map (Sample sample) {
        return SampleDto.builder()
                .id(sample.getId())
                .donationDateTime(getStringFromZonedDateTime(sample.getDonationDateTime()))
                .registrationDateTime(getStringFromZonedDateTime(sample.getRegistrationDateTime()))
                .sampleType(sample.getSampleType().getDescription())
                .build();

    }

    Sample map (SampleDto dto) {
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
