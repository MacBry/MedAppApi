package pl.mac.bry.sample;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleDto {

    private long id;
    private String donationDateTime;
    private String registrationDateTime;
    private String sampleType;
}
