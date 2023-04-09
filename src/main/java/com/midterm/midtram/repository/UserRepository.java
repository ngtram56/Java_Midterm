package com.midterm.midtram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.midterm.midtram.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User getUserByUsername(String username);
}
