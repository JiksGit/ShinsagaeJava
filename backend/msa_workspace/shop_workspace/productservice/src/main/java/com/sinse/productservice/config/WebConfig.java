package com.sinse.productservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;
    // 정적 자원에 접근 경로


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        log.debug("파일 경로는 ={}", uploadDir);
        // 웹 클라이언트가 어떤 url로 접근하게될 지 결정
        // 7777/resource/p1/uiso.jpg
        registry.addResourceHandler("/productapp/resource/**")
                .addResourceLocations("file:"+uploadDir+"/");
    }
}
