package com.sinse.electroshop.controller.dto;

import com.sinse.electroshop.domain.Store;
import lombok.Data;

@Data
public class ProductDTO {

    private int productId;
    private String productName;
    private int price;
    private String brand;
    private Store store;
}
