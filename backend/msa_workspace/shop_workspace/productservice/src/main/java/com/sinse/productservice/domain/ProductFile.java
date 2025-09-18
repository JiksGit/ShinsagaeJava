package com.sinse.productservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="productFile")
@Entity
@Data
public class ProductFile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer productFileId;
    private String Filename;
    private String originalName;
    private String contentType;
    private String filepath;
    private Long filesize;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

}
