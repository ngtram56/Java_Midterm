package com.midterm.midtram.service;

import com.midterm.midtram.model.Product;
import com.midterm.midtram.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public Product get(int id) {
		return productRepository.getProductById(id);
	}
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
	public Product add(Product u) {
		return productRepository.save(u);
	}
	
	public void delete(int id) {
		productRepository.deleteById(id);
	}

	public List<Product> filter(String cate, String brand) {
		return productRepository.filter(cate, brand);
	}

	public List<Product> filterName(String name) {
		return productRepository.getProductsByNameContains(name);
	}
}
