package pl.mac.bry.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.exceptions.InvalidPatientIdException;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.patient.PatientFacade;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderDtoMapper orderDtoMapper;
    private final PatientFacade patientFacade;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderDtoMapper orderDtoMapper,
                            PatientFacade patientFacade) {
        this.orderRepository = orderRepository;
        this.orderDtoMapper = orderDtoMapper;
        this.patientFacade = patientFacade;
    }

    @Override
    public Optional<OrderDto> findOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderDtoMapper::map);
    }

    @Override
    public Set<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderDtoMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<OrderDto> getAllPatientOrders(Long patientId) {
        Patient patient = patientFacade.getPatient(patientId)
                .orElseThrow(() -> new InvalidPatientIdException(patientId));
        return patient.getOrders()
                .stream()
                .map(orderDtoMapper::map)
                .collect(Collectors.toSet());
    }

    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order orderToSave = orderDtoMapper.map(orderDto);
        Order savedOrder = orderRepository.save(orderToSave);
        return orderDtoMapper.map(savedOrder);
    }

    @Override
    public Optional<OrderDto> updateOrder(Long orderId, OrderDto orderDto) {
        return Optional.empty();
    }

    @Override
    public void deleteOrder(Long orderId) {

    }
}
