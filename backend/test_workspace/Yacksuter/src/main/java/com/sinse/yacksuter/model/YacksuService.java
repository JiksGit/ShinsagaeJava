package com.sinse.yacksuter.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
@Service
public class YacksuService {

    private YacksuHandler yacksuHandler;

    public YacksuService(YacksuHandler yacksuHandler) {
        this.yacksuHandler = yacksuHandler;
    }

    public List<Yacksu> parse(HttpResponse<String> response) throws Exception{

        // SAXParser 생성
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        // 파일을 대상으로 파싱 시작
        parser.parse(response.body(), yacksuHandler); // 동기 방식으로 호출한다

        log.debug("파싱완료");

        return yacksuHandler.getYacksuList();
    }
}
