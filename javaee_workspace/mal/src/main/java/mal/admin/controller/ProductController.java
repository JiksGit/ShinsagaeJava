package mal.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import mal.domain.Color;
import mal.domain.Product;
import mal.domain.ProductColor;
import mal.domain.ProductSize;
import mal.domain.Size;
import mal.model.category.SubCategoryService;
import mal.model.category.TopCategoryService;
import mal.model.product.ProductService;
import mal.util.Paging;

@Slf4j
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//서비스 에게 일시킴 (느슨하게 보유, 즉 결합도를 낮추어서 보유,따라서 인터페이스로 보유)
	@Autowired
	private TopCategoryService topCategoryService;
	
	// 페이징 처리 객체를 보유
	@Autowired
	private Paging paging;
	
	// localhost:8888/admin/admin/product/registform
	@RequestMapping(value="/admin/product/registform")
	public String registform() {
		//상품 등록페이지를 보게되는 초기에, 상위 카테고리가채워져 있어야 함
		return "secure/product/regist";
	}
	
	//상품 등록 요청을 처리 
	@PostMapping("/admin/product/regist")
	@ResponseBody
	public String regist(Product product, int[] color, int[] size,   HttpServletRequest request) {
		
		
		//Product 객체의 멤버변수로 직접 html 과 매핑이 될 수 없는 경우엔, 우회하면 된다.. 
		//우회란? 파라미터를 별도로 받아서,  다시 Product 에 넣어준다
		
		//사용자가 선택한 색상이 3개라면, ProductColor의 인스턴스도 3개를 생성!!
		//스프링의 자동 매핑이 불가하므로, 개발자가 직접 생성 
		List<ProductColor> colorList=new ArrayList();
		List<ProductSize> sizeList = new ArrayList();
		
		for(int c : color) {
			Color cc= new Color();
			cc.setColor_id(c);
			
			ProductColor productColor  =new ProductColor();
			//유저가 선택한 색상 대입 
			productColor.setColor(cc);
			colorList.add(productColor);
		}
		
		for(int s : size) {
			Size ss = new Size();
			ss.setSize_id(s);
			
			ProductSize productSize = new ProductSize();
			productSize.setSize(ss);	
			sizeList.add(productSize);
		}
		
		//매핑완료 후, Product 에 대입 
		product.setColorList(colorList);
		product.setSizeList(sizeList);
		
		
		//모델 객체는 table을 반영한 객체이므로, 컨트롤러 영역에서 바로 파라미터를 받는 용도도 사용해서는 안됨
		//왜? 데이터베이스 컬럼명이 노출되기 때문에, 
		//해결책은? 클라이언트의 파라미터를 받는 용도의 객체를 별도로 둔다(DTO=Data Transfer Object)
		//DTO에서 Model 객체로 옮겨야 함..
		
		String savePath = request.getServletContext().getRealPath("/data");
		
		try {
			productService.regist(product, savePath);			
		} catch (Exception e) {
			productService.remove(product, savePath);
			e.printStackTrace();
		}
		
		//log.debug("product = "+product);
		//log.debug("photo = "+photo);
		//ServletContext context=request.getServletContext(); //jsp applicatio 내장 객체 
		//String realPath = context.getRealPath("/data");
		//log.debug("realPath is "+realPath);
		
		//4단계: DML은 저장할게 없다
		return "ok";
	}
	
	// 목록 요청 처리 : 요청이 들어오면 list.jsp를 응답정보로 보내야 한다.. 따라서
	// ResponseBody가 아닌 ModelAndView로 반환해야 함.
	@GetMapping("/admin/product/list")
	public ModelAndView getList(HttpServletRequest request) {
		// 3단계 : 목록 가져오기
		List productList = productService.selectAll();
		
		paging.init(productList, request);
		
		// 4단계 : 결과 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("productList", productList); // request.setAttribute("productList", productList");
		mav.addObject("paging", paging);
		mav.setViewName("secure/product/list");  // redirect 안하는 이유?
		
		return mav;
	}
	
	// 상세요청에 대한 처리
	@GetMapping("/admin/product/detail") // 매개변수는 쿼리 파라미터 name이 동일하면 자동 매핑
	public ModelAndView getDetail(int product_id) {
		ModelAndView mav = new ModelAndView("shop/detail");
		// 3단계 : 상세 내용 가져오기
		List topList = topCategoryService.selectAll();
		Product product = productService.select(product_id);
		// 4단계 : 저장하기
		mav.addObject("product", product);
		mav.addObject("topList", topList);
		
		return mav;
	}
}

























