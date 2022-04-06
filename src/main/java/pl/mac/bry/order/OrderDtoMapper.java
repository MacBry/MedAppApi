package pl.mac.bry.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.patient.PatientFacade;
import pl.mac.bry.referral_unit.ReferralUnitFacade;

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
                .build();
    }

    Order map (OrderDto dto) {
        Order order = Order.builder()
                .patient(patientFacade.map(dto.getPatient()))
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

}
