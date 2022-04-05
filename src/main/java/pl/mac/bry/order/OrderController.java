package pl.mac.bry.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Set<OrderDto>> getAllOrders () {
        if(orderService.getAllOrders().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/id/{orderIr}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") long  orderId) {
        return orderService.findOrderById(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/patient/{patientId}/orders")
    public ResponseEntity<Set<OrderDto>> getPatientAllOrders(@PathVariable("patientId") long patientId) {
        if(orderService.getAllPatientOrders(patientId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderService.getAllPatientOrders(patientId));
    }

    @PostMapping
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto) {
        OrderDto addedOrder = orderService.addOrder(orderDto);
        URI addedOrderUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{orderId}")
                .buildAndExpand(orderDto.getId())
                .toUri();
        return ResponseEntity.created(addedOrderUri).body(addedOrder);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable("orderId") long orderId, @RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderId, orderDto)
                .map(orders -> ResponseEntity.noContent().build())
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}