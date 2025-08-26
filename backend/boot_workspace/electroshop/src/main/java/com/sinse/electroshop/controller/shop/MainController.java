package com.sinse.electroshop.controller.shop;

import com.sinse.electroshop.domain.Product;
import com.sinse.electroshop.model.product.ProductDAO;
import com.sinse.electroshop.model.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;

    @GetMapping("/shop/main")
    public String index() {
        return "/electro/index";
    }
}
