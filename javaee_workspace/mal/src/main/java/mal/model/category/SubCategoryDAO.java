package mal.model.category;

import java.util.List;

import mal.domain.SubCategory;

public interface SubCategoryDAO {
	
	public List selectAll();
	public List selectByTopCategoryId(int topcategory_id);
	
}
