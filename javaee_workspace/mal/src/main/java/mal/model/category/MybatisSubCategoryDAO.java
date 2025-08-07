package mal.model.category;

import java.util.List;

import javax.transaction.Transactional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mal.domain.SubCategory;

@Repository
public class MybatisSubCategoryDAO implements SubCategoryDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate; // 순수 mybatis에서 SqlSession과 동일
	
	@Override
	public List selectAll() {
		return null;
	}
	@Override
	public List selectByTopCategoryId(int topcategory_id) {
		return sqlSessionTemplate.selectList("SubCategory.selectByTopCategoryId", topcategory_id);
	}


}
