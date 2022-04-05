package pl.mac.bry.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mac.bry.exceptions.CannotCreateOrderForPatientWithUnrealizedOrder;
import pl.mac.bry.exceptions.CannotCreateOrderForPatientWithoutAddressException;
import pl.mac.bry.exceptions.InvalidPatientIdException;
import pl.mac.bry.patient.Patient;
import pl.mac.bry.patient.PatientFacade;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
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
        if (orderToSave.getPatient().getPatientAddresses().isEmpty()){
            throw new CannotCreateOrderForPatientWithoutAddressException();
        }
        if (isPatientHaveUnrealizedOrder(orderToSave.getPatient())) {
            throw new CannotCreateOrderForPatientWithUnrealizedOrder();
        }
        else {
            orderToSave.setOrderDateTime(ZonedDateTime.now());
            Order savedOrder = orderRepository.save(orderToSave);
            return orderDtoMapper.map(savedOrder);
        }
    }

    @Override
    @Transactional
    public Optional<OrderDto> updateOrder(Long orderId, OrderDto orderDto) {
        return orderRepository.findById(orderId)
                .map(target -> setEntityFields(orderDto, target))
                .map(orderDtoMapper::map);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    private boolean isPatientHaveUnrealizedOrder(Patient patient) {
        Set<Order> orders = patient.getOrders();
        for(Order order : orders){
            if(order.getOrderStatus() == OrderStatus.REALIZED){
                return true;
            }
        }
        return false;
    }

    private Order setEntityFields(OrderDto orderDto, Order target) {
        if(orderDto.getPatient() != null) {
            target.setPatient(patientFacade.map(orderDto.getPatient()));
        }
        if(OrderStatus.valuesOfDescription(orderDto.getOrderStatus()) != target.getOrderStatus()) {
            target.setOrderStatus(OrderStatus.valuesOfDescription(orderDto.getOrderStatus()));
        }

        return target;
    }
}
