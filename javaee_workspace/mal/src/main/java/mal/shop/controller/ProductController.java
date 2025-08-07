package mal.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import mal.domain.Product;
import mal.domain.TopCategory;
import mal.model.category.TopCategoryService;
import mal.model.product.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private TopCategoryService topCategoryService;
	
	// 상품의 목록 요청
	@GetMapping("/product/list")
	public ModelAndView getProductList() {
		ModelAndView mav = new ModelAndView("shop/list");
		
		List<TopCategory> topList = topCategoryService.selectAll();
		List productList = productService.selectAll();
		
		mav.addObject("topList", topList);
		mav.addObject("productList", productList);
		return mav;
	}
	
	// 상세요청 처리
	@GetMapping("/product/detail")
	public ModelAndView getDetail(int product_id) {
		ModelAndView mav = new ModelAndView("shop/detail");
		
		List<TopCategory> topList = topCategoryService.selectAll();
		Product product = productService.select(product_id);
		
		mav.addObject("topList", topList);
		mav.addObject("product", product);
		
		return mav;
	}
	
}
