package com.sinse.bookapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinse.bookapp.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

//@Slf4j
//@RestController
//public class BookController {
//
//    @GetMapping("/books")
//    public ApiResponse getBooks(String title, String manageCd) {
//        log.debug("title={}, manageCd={}", title, manageCd);
//
//        ApiResponse response = new ApiResponse();
//
//        // header
//        ApiResponse.Header header = new ApiResponse.Header();
//        header.setTitle(title != null ? title : "");
//        header.setManageCd(manageCd != null ? manageCd : "");
//        header.setNumOfRows(5);
//        header.setPageNo(1);
//        response.setHeader(header);
//
//        // body
//        ApiResponse.Body book1 = new ApiResponse.Body();
//        book1.setLibName("서울시립도서관");
//        book1.setManageCode("LIB001");
//        book1.setCallNo("123.45 ABC");
//        book1.setTitle("테스트 책 제목");
//        book1.setAuthor("홍길동");
//        book1.setPublisher("테스트출판사");
//        book1.setPubYear(2025);
//        book1.setIsbn("978-89-000-0000-0");
//
//        ApiResponse.Body book2 = new ApiResponse.Body();
//        book2.setLibName("강남도서관");
//        book2.setManageCode("LIB002");
//        book2.setCallNo("678.90 XYZ");
//        book2.setTitle("Vue와 Spring 연동");
//        book2.setAuthor("이몽룡");
//        book2.setPublisher("코딩출판사");
//        book2.setPubYear(2024);
//        book2.setIsbn("978-89-111-1111-1");
//
//        response.setBody(List.of(book1, book2));
//
//        return response;
//    }
//}
@Slf4j
@RestController
public class BookController {

    private String serviceKey ="%2FH1gjQ0RIdraQdnpqe1aV%2BT34G1zGrDy1Zwb0PSh0Shg6Qytr2cekv4KuuHXxDJpDUiABfWTLGQRy%2BdywBVn%2Bg%3D%3D";

    @GetMapping("/books")
    public ApiResponse getBooks(String title, String manageCd) throws IOException, InterruptedException {
        title = title != null ? title : "";
        manageCd = manageCd != null ? manageCd : "";

        String baseUrl = "http://openapi-lib.sen.go.kr/openapi/service/lib/openApi";

        String url = baseUrl + "?" +
                "serviceKey="+serviceKey+
                "&title="+ URLEncoder.encode(title, StandardCharsets.UTF_8)+
                "&manageCd="+URLEncoder.encode(manageCd, StandardCharsets.UTF_8)+
                "&numOfRows="+URLEncoder.encode("5", StandardCharsets.UTF_8)+
                "&pageNo="+URLEncoder.encode("2", StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        ApiResponse apiResponse = mapper.readValue(response.body(), ApiResponse.class);

        return apiResponse;
    }
}
