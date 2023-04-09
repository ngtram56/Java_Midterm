package com.midterm.midtram.repository;

import com.midterm.midtram.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> getOrdersByUsername(String username);
}
