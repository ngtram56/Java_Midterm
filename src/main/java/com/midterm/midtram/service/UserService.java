package com.midterm.midtram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.midterm.midtram.model.User;
import com.midterm.midtram.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public User get(String username) {
		return userRepository.getUserByUsername(username);
	}
	
	public User add(User u) {
		return userRepository.save(u);
	}
	
	public void delete(int id) {
		userRepository.deleteById(id);
	}
}
