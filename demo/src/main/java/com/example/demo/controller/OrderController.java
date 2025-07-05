package com.example.demo.controller;

import com.example.demo.constants.APIConstants;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import com.example.demo.service.impl.OrderServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.constants.APIConstants.*;

@RestController
@RequestMapping(ORDERS)
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping(GET_ORDER)
    public Order getObjectByName(@PathVariable("name") String name,@PathVariable("orderedBy") String orderedBy){
        Order order = orderService.findOrder(name,orderedBy);
        return order;
    }

    @PostMapping(CREATE_ORDER)
    public ResponseEntity<Order> addOrder(@RequestParam("name") String name,@RequestParam("cost") double cost,@RequestParam("quantity") int quantity,@RequestParam("orderedBy") String orderedBy){
        Order order = orderService.createOrder(name,cost,quantity,orderedBy);
        return ResponseEntity.ok(order);
    }

    @PutMapping(UPDATE_ORDER)
    public ResponseEntity<Order> updateOrder(@RequestBody Order order){
        Order order1 = orderService.updateOrder(order);
        return ResponseEntity.ok(order1);
    }
}
