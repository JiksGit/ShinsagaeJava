package com.sinse.productservice.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Table(name="product")
@Entity
@Data
public class Product {

    @Id
    /* mybatis의 selectkey를 jpa로 구현하려면
        pk property에 GeneratedValue 명시해놓으면 데이터 insert 시 자동으로 채워넣는다
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer productId;

    @Column(name="product_name")
    private String productName;
    private String brand;
    private Integer price;
    private Integer discount;
    private String detail;

    // mybatis의 경우 association 대상
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;
    
    // mybatis의 경우 collection으로 수집함
    @OneToMany(mappedBy ="product", fetch = FetchType.EAGER)
    private List<ProductFile> productFileList = new ArrayList<>();
}
