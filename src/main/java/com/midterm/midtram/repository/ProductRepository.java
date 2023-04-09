package com.midterm.midtram.repository;

import com.midterm.midtram.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product p WHERE p.category = :cate AND p.brand = :brand", nativeQuery = true)
    List<Product> filter(@Param("cate") String cate, @Param("brand") String brand);

    Product getProductById(int id);
    Product getProductsByBrand(String brand);
    Product getProductsByCategory(String cate);
    Product getProductsByColor(String color);

    List<Product> getProductsByNameContains(String name);
}
