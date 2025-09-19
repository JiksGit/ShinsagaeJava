package com.sinse.productservice.domain;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="product_file")
@Entity
@Data
public class ProductFile {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer productFileId;
    private String filename;
    private String originalName;
    private String contentType;
    private String filepath;
    private Long filesize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

}
