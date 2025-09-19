package com.sinse.productservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="topcategory")
@Entity
@Data
public class TopCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topcategory_id")
    private Integer topCategoryId;

    @Column(name="topcategory_name")
    private String topCategoryName;
}
