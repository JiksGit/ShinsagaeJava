package com.sinse.childrenparkapp.model.park;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ParkHandler extends DefaultHandler {

    @Getter
    private List<Park> parkList;

    Park park;

    // 실행부가 어디 태그를 지나가는 지를 알 수 있는 기준 변수
    private boolean isEntercount = false;
    private boolean isInoutdoorcode = false;
    private boolean isProcessdate = false;

    // 문서가 시작될 때
    @Override
    public void startDocument() throws SAXException {
        log.debug("문서가 시작되었네요");
        parkList = new ArrayList<>();
    }

    // 시작 태그를 만났을 때
    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        log.debug("<"+tag+">");
        if(tag.equals("GetChildParkEnterStatQryVO")){
            park = new Park();
        } else if (tag.equals("entercount")){
            isEntercount = true;
        } else if (tag.equals("inoutdoorcode")){
            isInoutdoorcode = true;
        } else if (tag.equals("processdate")){
            isProcessdate = true;
        }
    }


    // 태그와 태그 사이의 컨텐츠를 만났을 때
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content =  new String(ch, start, length);
        log.debug(content);

        if(isEntercount) park.setEntercount(Integer.parseInt(content));
        if(isInoutdoorcode) park.setInoutdoorcode(content);
        if(isProcessdate) park.setProcessdate(content);
    }

    // 종료 태그를 만났을 때
    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
        log.debug("</"+tag+">");
        if(tag.equals("GetChildParkEnterStatQryVO")){
            parkList.add(park);
        } else if (tag.equals("entercount")){
            isEntercount = false;
        } else if (tag.equals("inoutdoorcode")){
            isInoutdoorcode = false;
        } else if (tag.equals("processdate")){
            isProcessdate = false;
        }
    }

    // 문서가 끝날 때
    @Override
    public void endDocument() throws SAXException {
        log.debug("xml 문서 파싱 후 담겨진 회원수는 " + parkList.size());
    }


}
