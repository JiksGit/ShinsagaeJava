package com.sinse.electroshop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //Mysql 의 auto_increment 매핑
    @JoinColumn(name = "store_id")
    private int storeId;

    @JoinColumn(name = "business_id")
    private String businessId;
    private String password;
    @JoinColumn(name = "store_name")
    private String storeName;
}
