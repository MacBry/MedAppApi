package pl.mac.bry.order;

import java.util.Optional;
import java.util.Set;

interface OrderService {

    Optional<OrderDto> findOrderById(Long orderId);

    Set<OrderDto> getAllOrders();

    Set<OrderDto> gePatientAllOrders(Long patientId);

    Set<OrderDto> getUnitAllOrders(Long unitId);

    OrderDto addOrder(OrderDto orderDto);

    Optional<OrderDto> updateOrder(Long orderId, OrderDto orderDto);

    void deleteOrder(Long orderId);
}
