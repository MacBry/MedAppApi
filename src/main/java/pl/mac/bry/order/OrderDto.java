package pl.mac.bry.order;

import lombok.*;

import pl.mac.bry.lab_test.LabTest;
import pl.mac.bry.lab_test.LabTestDto;
import pl.mac.bry.patient.PatientDto;

import java.util.Set;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class OrderDto {
    private Long id;
    private PatientDto patient;
    private String orderStatus;

    private Long UnitId;

    private Set<LabTestDto> labTests;
}
