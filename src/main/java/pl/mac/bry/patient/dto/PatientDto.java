package pl.mac.bry.patient.dto;

import lombok.*;
import org.springframework.stereotype.Service;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.patient.enums.ABOBloodGroup;
import pl.mac.bry.patient.enums.RhDFactor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Service
public class PatientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    private String aboGroup;
    private String rhdFactor;

}
