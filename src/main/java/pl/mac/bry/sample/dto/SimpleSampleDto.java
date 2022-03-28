package pl.mac.bry.sample.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleSampleDto {

    private long id;
    private String donationDateTime;
    private String registrationDateTime;
    private String sampleType;
    private long patientId;
}
