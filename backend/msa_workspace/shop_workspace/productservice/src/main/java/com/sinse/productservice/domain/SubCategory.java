package com.sinse.productservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="subcategory")
@Entity
@Data
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private Integer subCategoryId;

    @Column(name="subcategory_name")
    private String subcategoryName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="topcategory_id")
    private TopCategory topcategory;
}
