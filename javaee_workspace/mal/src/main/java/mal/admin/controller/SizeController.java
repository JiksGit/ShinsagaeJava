package mal.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mal.model.product.SizeService;

@Controller
public class SizeController {

	@Autowired
	private SizeService sizeService;
	
	@GetMapping("/admin/size/list")
	@ResponseBody
	public List selectAll() {
		return sizeService.selectAll();
	}
}
