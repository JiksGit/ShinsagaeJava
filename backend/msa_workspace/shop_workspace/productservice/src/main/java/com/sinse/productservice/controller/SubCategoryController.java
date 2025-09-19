package com.sinse.productservice.controller;

import com.sinse.productservice.controller.dto.SubCategoryDTO;
import com.sinse.productservice.domain.SubCategory;
import com.sinse.productservice.model.category.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/productapp")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @GetMapping("/subcategories")
    public ResponseEntity<?> getListByTopCategoryId(int topCategoryId){
        List<SubCategory> subCategoryList =  subCategoryService.selectAll(topCategoryId);

        List<SubCategoryDTO> subCategoryDTOList = subCategoryList.stream()
                .map(subCategory -> {
                    SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
                    subCategoryDTO.setSubcategoryName(subCategory.getSubcategoryName());
                    subCategoryDTO.setSubCategoryId(subCategory.getSubCategoryId());
                    return subCategoryDTO;
                }).toList();

        return ResponseEntity.ok(Map.of("result",  subCategoryDTOList));
    }
}
