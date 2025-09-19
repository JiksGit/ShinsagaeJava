package com.sinse.productservice.controller;

import com.sinse.productservice.controller.dto.ProductDTO;
import com.sinse.productservice.controller.dto.ProductFileDTO;
import com.sinse.productservice.controller.dto.SubCategoryDTO;
import com.sinse.productservice.domain.Product;
import com.sinse.productservice.domain.SubCategory;
import com.sinse.productservice.model.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/productapp")
public class ProductController {

    private ProductService productService;

    public  ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> select(@PathVariable int productId) {
        Product product = productService.select(productId);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setProductName(product.getProductName());
        productDTO.setBrand(product.getBrand());
        productDTO.setPrice(product.getPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setDetail(product.getDetail());

        // SubCategory 옮기기
        SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
        subCategoryDTO.setSubCategoryId(product.getSubCategory().getSubCategoryId());
        subCategoryDTO.setSubcategoryName(product.getSubCategory().getSubcategoryName());

        productDTO.setSubCategoryDTO(subCategoryDTO);

        // ProductFile도 옮기기 List에 담기(함수형 프로그래밍)
        List<ProductFileDTO> productFileDTOList = product.getProductFileList().stream()
                .map(pf ->{
                    ProductFileDTO productFileDTO = new ProductFileDTO();
                    productFileDTO.setProductFileId(pf.getProductFileId());
                    productFileDTO.setFilename(pf.getFilename());
                    productFileDTO.setContentType(pf.getContentType());
                    productFileDTO.setOriginalName(pf.getOriginalName());
                    productFileDTO.setFilesize(pf.getFilesize());
                    productFileDTO.setFilepath(pf.getFilepath());

                    return productFileDTO;
                })
                .toList();

        productDTO.setProductFileDTO(productFileDTOList);
        return ResponseEntity.ok(Map.of("result", productDTO));
    }

    @GetMapping("/products")
    public ResponseEntity<?> products() {
        List<Product> productList = productService.selectAll();

        List<ProductDTO> productDTOList = productList.stream()
                .map(product->{
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setProductId(product.getProductId());
                    productDTO.setProductName(product.getProductName());
                    productDTO.setPrice(product.getPrice());
                    productDTO.setBrand(product.getBrand());
                    productDTO.setDiscount(product.getDiscount());
                    productDTO.setDetail(product.getDetail());

                    // SubCategory 옮기기
                    SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
                    subCategoryDTO.setSubCategoryId(product.getSubCategory().getSubCategoryId());
                    subCategoryDTO.setSubcategoryName(product.getSubCategory().getSubcategoryName());

                    productDTO.setSubCategoryDTO(subCategoryDTO);

                    // ProductFile도 옮기기 List에 담기(함수형 프로그래밍)
                    List<ProductFileDTO> productFileDTOList = product.getProductFileList().stream()
                                    .map(pf ->{
                                        ProductFileDTO productFileDTO = new ProductFileDTO();
                                        productFileDTO.setProductFileId(pf.getProductFileId());
                                        productFileDTO.setFilename(pf.getFilename());
                                        productFileDTO.setContentType(pf.getContentType());
                                        productFileDTO.setOriginalName(pf.getOriginalName());
                                        productFileDTO.setFilesize(pf.getFilesize());
                                        productFileDTO.setFilepath(pf.getFilepath());

                                        return productFileDTO;
                                    })
                                    .toList();

                    productDTO.setProductFileDTO(productFileDTOList);

                    return productDTO;
                })
                .toList();

        return ResponseEntity.ok(Map.of("result", productDTOList));
    }
    //파일업로드 요청 처리
    @PostMapping(value="/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> regist(@ModelAttribute ProductDTO productDTO, @RequestPart List<MultipartFile> files) {
        log.debug("넘겨받은 이미지 수는 "+files.size());

        log.debug("서브 카테고리는 "+productDTO.getSubCategoryDTO().getSubCategoryId());
        log.debug("상품명"+productDTO.getProductName());
        log.debug("브랜드"+productDTO.getBrand());
        log.debug("가격"+productDTO.getPrice());
        log.debug("할인가"+productDTO.getDiscount());
        log.debug("상세설명"+productDTO.getDetail());

        //서비스에게 !!
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setBrand(productDTO.getBrand());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        product.setDetail(productDTO.getDetail());
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryId(productDTO.getSubCategoryDTO().getSubCategoryId());
        product.setSubCategory(subCategory);

        productService.save(product, files);

        return ResponseEntity.ok(Map.of("result", "업로드 성공"));
    }

}
