package com.sinse.yacksuter.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class YacksuHandler extends DefaultHandler {

    @Getter
    private List<Yacksu> yacksuList;

    Yacksu yacksu;

    private boolean isBcode = false;
    private boolean isGroupCode = false;
    private boolean isIncredient = false;
    private boolean isLocation = false;
    private boolean isPosx = false;
    private boolean isPosy = false;
    private boolean isTitle = false;
    private boolean isWaterBase = false;
    private boolean isWaterType = false;

    @Override
    public void startDocument() throws SAXException {
        log.debug("startDocument");
        yacksuList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        log.debug("<" + tag + ">");
        if(tag.equals("list")) {
            yacksu = new Yacksu();
        } else if (tag.equals("bcode")){
            isBcode = true;
        } else if (tag.equals("groupCode")) {
            isGroupCode = true;
        } else if (tag.equals("incredient")) {
            isIncredient = true;
        } else if (tag.equals("location")) {
            isLocation = true;
        } else if (tag.equals("posx")) {
            isPosx = true;
        } else if (tag.equals("posy")) {
            isPosy = true;
        } else if (tag.equals("title")) {
            isTitle = true;
        } else if (tag.equals("waterbase")) {
            isWaterBase = true;
        } else if (tag.equals("watertype")) {
            isWaterType = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        log.debug(content);

    }

    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
        log.debug("</" + tag + ">");
        if(tag.equals("list")) {
            yacksuList.add(yacksu);
        } else if (tag.equals("bcode")){
            isBcode = false;
        } else if (tag.equals("groupCode")) {
            isGroupCode = false;
        } else if (tag.equals("incredient")) {
            isIncredient = false;
        } else if (tag.equals("location")) {
            isLocation = false;
        } else if (tag.equals("posx")) {
            isPosx = false;
        } else if (tag.equals("posy")) {
            isPosy = false;
        } else if (tag.equals("title")) {
            isTitle = false;
        } else if (tag.equals("waterbase")) {
            isWaterBase = false;
        } else if (tag.equals("watertype")) {
            isWaterType = false;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        log.debug("endDocument");
        log.debug("xml 문서 파싱 후 담겨진 약수터수는 " + yacksuList.size());
    }
}
