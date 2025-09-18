package com.sinse.productservice.model.product;

import com.sinse.productservice.domain.Product;
import com.sinse.productservice.domain.ProductFile;
import com.sinse.productservice.util.UploadManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final JpaProductRepository jpaProductRepository;
    private final JpaProductFileRepository jpaProductFileRepository;
    private final UploadManager uploadManager;

    public ProductServiceImpl( JpaProductRepository jpaProductRepository, JpaProductFileRepository jpaProductFileRepository, UploadManager uploadManager) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaProductFileRepository = jpaProductFileRepository;
        this.uploadManager = uploadManager;
    }

    @Override
    public void save(Product product, List<MultipartFile> files) {
        Product saveProduct = jpaProductRepository.save(product);

        int product_id = saveProduct.getProductId();
        log.debug("상품 insert 후 반환된 product_id={}", product_id);

        /*---------------------------------------------
           이미지 수만큼 UploadManager의 store()를 호출하자
           단, 트랜잭션의 대상은 파일이 아니라 DB만 가능하므로, 파일 저장 시 하나라도 실패하면
           비록 트랜잭션 대상은 아니지만 , 처음부터 파일이 없었던 것으로 돌려놓아야 하므로, 디렉토리 자체를 날리자
        --------------------------------------------- */
        // 이미지 수만큼 반복문을 돌리되, 전통적인 반복문이 아닌 선언적 프로그래밍 방식으로 표현
        List<ProductFile> productFileList = files.stream()
                .map(file->{
                    try {
                        return uploadManager.store(file, product);
                    } catch (IOException e) {
                        // 에러시 폴더 삭제 코드 필요
                        throw new RuntimeException(e);
                    }
                }).toList();

        productFileList.forEach(productFile -> {
            jpaProductFileRepository.save(productFile);
        });
    }
}
