package com.sinse.productservice.controller;

import com.sinse.productservice.controller.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<?> products() {
        return ResponseEntity.ok(Map.of("data", List.of("노트북", "스마트폰", "태블릿")));
    }

    // 파일업로드 요청 처리
    @PostMapping(value="/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> regist(@ModelAttribute ProductDTO productDTO, @RequestPart List<MultipartFile> files) {
        log.debug("넘겨받은 상품명은 : " +productDTO.getProductName());
        log.debug("넘겨받은 id는 : " +productDTO.getProductId());
        log.debug("넘겨받은 브랜드는 : " +productDTO.getBrand());
        log.debug("넘겨받은 가격은 : " +productDTO.getPrice());
        log.debug("넘겨받은 파일 수는 : " +productDTO.getFiles().size());

        return ResponseEntity.ok(Map.of("result", "업로드 성공"));
    }

}
