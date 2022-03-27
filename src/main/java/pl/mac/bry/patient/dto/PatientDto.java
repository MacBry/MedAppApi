package pl.mac.bry.patient.dto;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String aboGroup;
    private String rhdFactor;

}
