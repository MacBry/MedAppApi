package pl.mac.bry.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.patient.PatientFacade;

@Service
class OrderDtoMapper {

    private final PatientFacade patientFacade;

    @Autowired
    public OrderDtoMapper(PatientFacade patientFacade) {
        this.patientFacade = patientFacade;
    }

    OrderDto map (Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .patient(patientFacade.map(order.getPatient()))
                .build();
    }

    Order map (OrderDto dto) {
        Order order = Order.builder()
                .patient(patientFacade.map(dto.getPatient()))
                .build();
        patientFacade.getPatient(dto.getPatient().getId())
                .ifPresent(order::setPatient);
        return order;
    }

}
