package com.sinse.productservice.controller.dto;

import lombok.Data;

// JPA의 의한 Entity가 아니므로, 프락시 객체가 붙어있지 않기 때문에,
// 즉 순수한 객체이므로 json으로 변경시에 문제가없다
@Data
public class ProductFileDTO {

    private Integer productFileId;
    private String filename;
    private String originalName;
    private String contentType;
    private String filepath;
    private Long filesize;

}
