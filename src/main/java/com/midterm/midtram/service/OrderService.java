package com.midterm.midtram.service;

import com.midterm.midtram.model.Order;
import com.midterm.midtram.repository.OrderDetailRepository;
import com.midterm.midtram.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllByUsername(String username) {
        return orderRepository.getOrdersByUsername(username);
    }

    public Order add(Order order) {
        return orderRepository.save(order);
    }
}
