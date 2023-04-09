package com.midterm.midtram.service;

import com.midterm.midtram.model.OrderDetail;
import com.midterm.midtram.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }
    public OrderDetail add(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
