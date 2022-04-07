package pl.mac.bry.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.lab_test.LabTest;
import pl.mac.bry.lab_test.LabTestDto;
import pl.mac.bry.patient.PatientFacade;
import pl.mac.bry.referral_unit.ReferralUnitFacade;

import java.util.HashSet;
import java.util.Set;

@Service
class OrderDtoMapper {

    private final PatientFacade patientFacade;
    private final ReferralUnitFacade unitFacade;

    @Autowired
    public OrderDtoMapper(PatientFacade patientFacade, ReferralUnitFacade unitFacade) {
        this.patientFacade = patientFacade;
        this.unitFacade = unitFacade;
    }

    OrderDto map (Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .patient(patientFacade.map(order.getPatient()))
                .orderStatus(order.getOrderStatus().getDescription())
                .labTests(toLabTestDto(order.getLabTests()))
                .build();
    }

    Order map (OrderDto dto) {
        Order order = Order.builder()
                .patient(patientFacade.map(dto.getPatient()))
                //set Lab Tests to Order
                .labTests(toLabTest(dto.getLabTests()))
                .build();
        //set Patient to Order
        patientFacade.getPatient(dto.getPatient().getId())
                .ifPresent(order::setPatient);

        //set initial status to Order
        order.setOrderStatus(OrderStatus.UNREALIZED);

        //set Referral Unit to Order
        unitFacade.getReferralUnit(dto.getUnitId())
                .ifPresent(order::setReferralUnit);

        return order;
    }

    private Set<LabTest> toLabTest (Set<LabTestDto> dtoSet) {
        Set<LabTest> resultSet = new HashSet<>();
        for (LabTestDto dto : dtoSet) {
            LabTest test = new LabTest();
            test.setId(dto.getId());
            test.setLabTestName(dto.getLabTestName());
            test.setAlterTestName(dto.getAlterTestName());
            test.setLowerReferenceValue(dto.getLowerReferenceValue());
            test.setUpperReferenceValue(dto.getUpperReferenceValue());
            test.setResult(dto.getResult());
            resultSet.add(test);
        }
        return resultSet;
    }

    private Set<LabTestDto> toLabTestDto (Set<LabTest> dtoSet) {
        Set<LabTestDto> resultSet = new HashSet<>();
        for (LabTest dto : dtoSet) {
            LabTestDto test = new LabTestDto();
            test.setId(dto.getId());
            test.setLabTestName(dto.getLabTestName());
            test.setAlterTestName(dto.getAlterTestName());
            test.setLowerReferenceValue(dto.getLowerReferenceValue());
            test.setUpperReferenceValue(dto.getUpperReferenceValue());
            test.setResult(dto.getResult());
            resultSet.add(test);
        }
        return resultSet;
    }

}
