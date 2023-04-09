package com.midterm.midtram.service;

import com.midterm.midtram.model.Cart;
import com.midterm.midtram.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    public List<Cart> getCartsByUsername(String username) {
        return cartRepository.getCartsByUsername(username);
    }

    public Cart add(Cart cart) {
        return cartRepository.save(cart);
    }

    public void delete(int id) {
        cartRepository.deleteById(id);
    }
}
