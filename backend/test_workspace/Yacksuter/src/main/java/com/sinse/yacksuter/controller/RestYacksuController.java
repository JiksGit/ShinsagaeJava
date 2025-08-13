package com.sinse.yacksuter.controller;

import com.sinse.yacksuter.model.Yacksu;
import com.sinse.yacksuter.model.YacksuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
public class RestYacksuController {

    private String serviceKey="KKjOD9q1hOtnI9nQNJwZrixRw%2FAnZRztIV9zSPRVpRfTyvIVou7z2GxyfZYKyuQsu0Zwqr90qLHOZhblv2Dm7Q%3D%3D";

    private YacksuService yacksuService;

    public RestYacksuController(YacksuService yacksuService) {
        this.yacksuService = yacksuService;
    }

    @GetMapping("/test")
    public String test(){
        return "my app is success";
    }

    @GetMapping("/yacksuters")
    public List<Yacksu> yacksuList(String bcode) throws Exception {
        String baseUrl="https://apis.data.go.kr/6460000/jnYaksoo";

        //if(store_name.length()<1)store_name="";

        //파라미터 설정
        String url=baseUrl+"?" +
                "serviceKey="+serviceKey+
                "&bcode="+ URLEncoder.encode(bcode, StandardCharsets.UTF_8)+
                "&pageSize="+URLEncoder.encode("10", StandardCharsets.UTF_8)+
                "&startPage="+URLEncoder.encode("0", StandardCharsets.UTF_8);

        //HttpUrlConnection 보다 개선
        HttpClient client = HttpClient.newHttpClient();

        //요청 정보 객체 생성
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/xml") // XML 요청 명시
                .GET()
                .build();

        //Open API 서버에 요청 시도
        HttpResponse<String> response =client.send(request, HttpResponse.BodyHandlers.ofString());

        log.info("API Response: {}", response.body());
        return yacksuService.parse(response);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){
        return e.getMessage();
    }
}
