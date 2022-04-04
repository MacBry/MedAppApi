package pl.mac.bry.order;

import lombok.*;

import pl.mac.bry.patient.PatientDto;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class OrderDto {
    private Long id;
    private PatientDto patient;
    private String orderStatus;

}
