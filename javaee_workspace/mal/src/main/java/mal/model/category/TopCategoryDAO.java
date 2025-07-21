package mal.model.category;

import java.util.List;

import mal.domain.TopCategory;

public interface TopCategoryDAO {
	
	public List selectAll();
	public TopCategory select(int topcategory_id);

}
