package pl.mac.bry.patient;

import org.springframework.stereotype.Service;
import pl.mac.bry.patient.dto.PatientDto;
import pl.mac.bry.patient.enums.ABOBloodGroup;
import pl.mac.bry.patient.enums.RhDFactor;

@Service
class PatientDtoMapper {

    PatientDto map (Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .pesel(patient.getPesel())
                .aboGroup(patient.getAboGroup().getDescription())
                .rhdFactor(patient.getRhdFactor().getDescription())
                .build();
    }

    Patient map (PatientDto patientDto) {
        return Patient.builder()
                .firstName(patientDto.getFirstName())
                .lastName(patientDto.getLastName())
                .pesel(patientDto.getPesel())
                .aboGroup(getAboBloodGroup(patientDto))
                .rhdFactor(getRhdFactor(patientDto))
                .build();
    }

    private RhDFactor getRhdFactor(PatientDto patientDto) {
        return RhDFactor.valueOFDescription(patientDto.getRhdFactor());
    }

    private ABOBloodGroup getAboBloodGroup(PatientDto patientDto) {
        return ABOBloodGroup.valueOfDescription(patientDto.getAboGroup());
    }
}
