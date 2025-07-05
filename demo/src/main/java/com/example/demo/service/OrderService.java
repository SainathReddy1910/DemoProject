package com.example.demo.service;

import com.example.demo.entity.Order;

public interface OrderService {
    Order createOrder(String name,double cost,int quantity,String orderBy);
    Order updateOrder(Order order);
    Order findOrder(String name,String orderedBy);
}
