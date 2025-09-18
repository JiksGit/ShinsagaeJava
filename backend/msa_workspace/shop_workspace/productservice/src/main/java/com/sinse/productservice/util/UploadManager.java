package com.sinse.productservice.util;

import com.sinse.productservice.domain.Product;
import com.sinse.productservice.domain.ProductFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class UploadManager {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ProductFile store(MultipartFile file, Product product) throws IOException {
        // 루트 디렉토리가 존재하지 않을 경우 생성
        createDirectory(uploadDir);
        Path savePath = createDirectory(uploadDir+"/p"+product.getProductId());

        /*------------------------------------------
        파일명 생성
        ------------------------------------------ */
        String originalName =  file.getOriginalFilename();
        String extension = null;

        if(originalName != null && originalName.contains(".")){
            extension = originalName.substring(originalName.lastIndexOf("."));
        }
        String newFilename = UUID.randomUUID().toString()+extension;
        log.debug("개발자가 정의한 새로운 파일명은 {}", newFilename);

        /*------------------------------------------
        파일 저장
        ------------------------------------------ */
        Path targetlocation = savePath.resolve(newFilename); // os에 적절한 경로로 변경
        Files.copy(file.getInputStream(), targetlocation, StandardCopyOption.REPLACE_EXISTING);

        // 저장 정보를 담을 ProductFile을 반환
        ProductFile productFile = new ProductFile();
        productFile.setProduct(product);
        productFile.setFilename(newFilename);
        productFile.setOriginalName(originalName);
        productFile.setContentType(file.getContentType());
        productFile.setFilepath(targetlocation.toString());
        productFile.setFilesize(file.getSize());

        return productFile;
    }

    // 디렉토리 생성 메서드 정의
    // createDirectory("c://upload"), createDirectory("p23")
    public Path createDirectory(String path) throws IOException {
        Path dir = Paths.get(path);
        Path savePath = Paths.get(path).toAbsolutePath().normalize();
        if(!(Files.exists(dir) && Files.isDirectory(dir))){
            Files.createDirectories(dir);
        } else {
            log.debug("{} 디렉토리가 이미 존재함 ", path);
        }
        log.debug("savePath 경로 : {}", savePath);
        return savePath;
    }
}
