package pl.mac.bry.sample.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComplexSampleDto {

    private long id;
    private String donationDateTime;
    private String registrationDateTime;
    private String sampleType;

    private String firstName;
    private String lastName;
    private String pesel;
    private String aboGroup;
    private String rhdFactor;

}
