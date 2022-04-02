package pl.mac.bry.patient;

import org.springframework.stereotype.Service;

@Service
class PatientDtoMapper {

    PatientDto map (Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .pesel(patient.getPesel())
                .build();
    }

    Patient map (PatientDto patientDto) {
        return Patient.builder()
                .firstName(patientDto.getFirstName())
                .lastName(patientDto.getLastName())
                .pesel(patientDto.getPesel())

                .build();
    }

}
