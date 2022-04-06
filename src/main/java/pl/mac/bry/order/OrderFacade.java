package pl.mac.bry.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderFacade {

    private final OrderRepository repository;
    private final OrderDtoMapper dtoMapper;

    @Autowired
    public OrderFacade(OrderRepository repository, OrderDtoMapper dtoMapper) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
    }

    public Optional<Order> getOrder(long orderId) {
        return repository.findById(orderId);
    }

    public OrderDto map (Order order) {
        return dtoMapper.map(order);
    }

    public Order map (OrderDto dto) {
        return  dtoMapper.map(dto);
    }
}
