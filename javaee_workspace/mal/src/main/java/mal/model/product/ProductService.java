package mal.model.product;

import mal.domain.Product;

public interface ProductService {

	public void regist(Product product, String savePath); //insert sql + 파일 업로드, 트랜잭션 까지 진행
	
}
