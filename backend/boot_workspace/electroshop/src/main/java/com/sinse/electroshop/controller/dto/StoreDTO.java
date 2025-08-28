package com.sinse.electroshop.controller.dto;

import lombok.Data;

//Data Transfer Object
@Data
public class StoreDTO {
    private int storeId;
    private String id;
    private String pwd;
    private String storeName;
}
