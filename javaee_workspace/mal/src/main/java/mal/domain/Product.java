package mal.domain;

import javax.persistence.Entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {

	private int product_id;
	private String product_name;
	private String brand;
	private int price;
	private int discount;
	private String introduce;
	private String detail;
	// private MultipartFile[] photo; // name="photo"와 동일해야함
	private SubCategory subCategory;
	
}
