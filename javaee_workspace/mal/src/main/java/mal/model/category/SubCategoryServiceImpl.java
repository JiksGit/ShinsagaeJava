package mal.model.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mal.domain.SubCategory;
import mal.domain.TopCategory;

// 컨트롤러가 느슨하게, 서비스를 보유하게 하려면(DI) 인터페이스로 정의
@Service
public class SubCategoryServiceImpl implements SubCategoryService{

	@Autowired
	private SubCategoryDAO subCategoryDAO;
	
	@Override
	public List selectAll() {
		return subCategoryDAO.selectAll();
	}
	public List selectByTopCategoryId(int topcategory_id) {
		return subCategoryDAO.selectByTopCategoryId(topcategory_id);
	};
	
}
