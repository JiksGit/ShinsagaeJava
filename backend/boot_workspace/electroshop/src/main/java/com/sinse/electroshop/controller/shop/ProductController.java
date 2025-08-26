package com.sinse.electroshop.controller.shop;

import com.sinse.electroshop.domain.Product;
import com.sinse.electroshop.model.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/list")
    @ResponseBody
    public List<Product> getProductList() {
        return productService.getList();
    }

    // 상품 상세 요청 처리
    @GetMapping("/product/detail")
    public String getDetail(@RequestParam int product_id, Model model) {
         Product product = productService.getDetail(product_id);
         model.addAttribute("product", product);

         return "/electro/product";
    }
}
