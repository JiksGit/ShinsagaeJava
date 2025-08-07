package mal.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mal.model.category.TopCategoryService;

// 스프링이 관리하는 빈들 중, 특별히 그 용도가 정해진 빈을 가리켜 컴포넌트라 한다
// 특히 이 컴포넌트들은, ComponentScan의 대상이 될 수 있다. 즉 일일이 @Bean 등록하지 않아도 됨
// 잘 알려진 컴포넌트에는 @Controller, @Server, @Repository, @Component
@Controller
public class TopCategoryController {

	@Autowired
	private TopCategoryService topCategoryService;
	
	@GetMapping("/admin/topcategory/list")
	@ResponseBody // 응답 데이터 형식이 html 문서가 아닌, json 등의 순수 데이터 일 경우
					// ViewResolver인 InternalResourceViewResolver가 동작하지 않음
					// 접두어, 접미어의 조합을 시도하지 않음. 즉, response.setContentType("application/json") 효과
	public List selectAll() { // json으로 보내야 함
		// List를 json 문자열로 변환해야한다 (ajax이기 때문) -> @ResponseBody..
		List topList = topCategoryService.selectAll();
		// 스프링을 사용하지 않을 경우, 개발자가 List를 Gson 또는 직접 json 문자열로 응답 정보를 만들어야 하지만
		// 스프링은 개발에 필요한 모든 것을 지원하는 프레임 웍이므로, 코드를 단순화시켜줌
		return topList;
	}
	
}


