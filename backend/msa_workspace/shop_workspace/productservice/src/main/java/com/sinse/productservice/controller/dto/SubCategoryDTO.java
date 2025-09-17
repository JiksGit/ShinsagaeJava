package com.sinse.productservice.controller.dto;

import lombok.Data;

@Data
public class SubCategoryDTO {

    private int subcategoryId;
    private String subcategoryName;
    private TopCategoryDTO topcategoryDTO;
}
