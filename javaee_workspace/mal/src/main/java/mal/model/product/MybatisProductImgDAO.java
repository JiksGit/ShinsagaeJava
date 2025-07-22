package mal.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mal.domain.ProductImg;
import mal.exception.ProductImgException;

@Repository
public class MybatisProductImgDAO implements ProductImgDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(ProductImg productImg) throws ProductImgException{
		int result = sqlSessionTemplate.insert("ProductImg.insert", productImg);
		
		if(result < 1) {
			throw new ProductImgException("상품 이미지 정보 등록 실패");
		}
	}

}
