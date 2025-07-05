package com.example.demo.service.impl;

import com.example.demo.entity.Order;
import com.example.demo.enums.Status;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.demo.constants.APIConstants.ORDERS_ROUTING_KEY;
import static com.example.demo.constants.APIConstants.EXCHANGE_NAME;

@Component
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    public OrderServiceImpl(OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Order createOrder(String name, double cost, int quantity, String orderBy) {
        log.info("Creating the object with the provided details. ");
        Order orderCreated = orderRepository.findByNameAndOrderedBy(name,orderBy);
        if(orderCreated==null)
            orderCreated = new Order(UUID.randomUUID(),name,cost,orderBy,quantity, Status.PROCESSING);
        else{
            orderCreated.setQuantity(quantity);
            orderCreated.setCost(cost);
        }
        log.info("Object created {} ", orderCreated);
        orderRepository.save(orderCreated);
        log.info("Publishing the order to generate the bill.");
        rabbitTemplate.convertAndSend(EXCHANGE_NAME,ORDERS_ROUTING_KEY,orderCreated);
        log.info("Message sent to bill generator successfully for {}: ", orderCreated);
        return orderCreated;
    }

    @Override
    public Order updateOrder(Order order) {
        Order order1 = orderRepository.findByNameAndOrderedBy(order.getName(),order.getOrderedBy());
        order1.setCost(order.getCost());
        order1.setQuantity(order.getQuantity());
        orderRepository.save(order1);
        return order1;
    }

    @Override
    public Order findOrder(String name,String orderedBy){
        Order order = orderRepository.findByNameAndOrderedBy(name,orderedBy);
        return order;
    }
}
