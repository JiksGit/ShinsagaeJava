package mal.model.category;

import java.util.List;

import mal.domain.SubCategory;
import mal.domain.TopCategory;

// 컨트롤러가 느슨하게, 서비스를 보유하게 하려면(DI) 인터페이스로 정의
public interface SubCategoryService {

	public List selectAll();
	public List selectByTopCategoryId(int topcategory_id);
	
}
