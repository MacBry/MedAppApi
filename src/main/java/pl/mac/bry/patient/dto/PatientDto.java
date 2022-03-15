package pl.mac.bry.patient.dto;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.patient.enums.ABOBloodGroup;
import pl.mac.bry.patient.enums.RhDFactor;

@Data
@Service
public class PatientDto {

    private String firstName;
    private String lastName;
    private String pesel;
    private String aboGroup;
    private String rhdFactor;

    PatientDto map (Patient patient) {
        PatientDto dto = new PatientDto();
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setPesel(patient.getPesel());
        dto.setAboGroup(patient.getAboGroup().getDescription());
        dto.setRhdFactor(patient.getRhdFactor().getDescription());
        return dto;
    }
}
