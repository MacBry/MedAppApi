package pl.mac.bry.patient;

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

}
