package com.sinse.electroshop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "product_id")
    private int productId;

    @JoinColumn(name = "product_name")
    private String productName;
    private int price;
    private String brand;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false)
    private Store store;
}
