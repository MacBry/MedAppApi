package pl.mac.bry.order;

import lombok.*;
import pl.mac.bry.address.Address;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.patient.PatientDto;
import pl.mac.bry.referralUnit.ReferralUnit;
import pl.mac.bry.referralUnit.ReferralUnitDto;
import pl.mac.bry.test.Test;
import pl.mac.bry.test.TestDto;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class OrderDto {
    private Long id;

    private PatientDto patient;

    private Address address;

    private ReferralUnitDto referralUnit;

    private Set<TestDto> tests;

}
