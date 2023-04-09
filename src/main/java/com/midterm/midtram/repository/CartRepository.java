package com.midterm.midtram.repository;

import com.midterm.midtram.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> getCartsByUsername(String username);
}
