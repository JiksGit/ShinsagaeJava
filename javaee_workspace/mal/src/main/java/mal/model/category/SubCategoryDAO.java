package mal.model.category;

import java.util.List;

import mal.domain.SubCategory;

public interface SubCategoryDAO {

	public List selectAll();
	public SubCategory select(int subcategory_id);
	
}
