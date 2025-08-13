package com.sinse.childrenparkapp.model.park;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
@Service
public class ParkService {

    private ParkHandler parkHandler;

    public ParkService(ParkHandler parkHandler) {
        this.parkHandler = parkHandler;
    }

    // 파싱 시도하기
    public List<Park> parse(String responseBody) throws Exception {
        // SAXParser 생성
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        // String을 InputSource로 변환
        InputSource is = new InputSource(new StringReader(responseBody));

        // 파싱 시작
        parser.parse(is, parkHandler);

        log.debug("파싱완료");

        return parkHandler.getParkList();
    }
}
