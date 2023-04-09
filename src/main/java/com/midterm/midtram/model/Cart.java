package com.midterm.midtram.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String username;

    @Column
    private int product_id;

    @Column
    private String product_name;

    @Column
    private double product_price;

    @Column
    private int quantity;
    @Column
    private String product_image;



    public Cart(int id, String username, int product_id, String product_name, double product_price, int quantity, String product_image) {
        this.id = id;
        this.username = username;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.quantity = quantity;
        this.product_image = product_image;
    }
}
