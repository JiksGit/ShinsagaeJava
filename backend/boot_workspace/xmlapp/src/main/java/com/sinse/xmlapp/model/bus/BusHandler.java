package com.sinse.xmlapp.model.bus;

import com.sinse.xmlapp.domain.Item;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

// Item이라는 xml을 해석하여, java로 변환 (convert)
@Component
public class BusHandler extends DefaultHandler {

    @Getter
    List<Item> itemList;
    Item item;

    private boolean bstopid = false;
    private boolean bstopnm = false;
    private boolean arsno = false;
    private boolean gpsx = false;
    private boolean gpsy = false;
    private boolean stoptype = false;

    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        if(tag.equals("items")){
            itemList = new ArrayList<Item>();
        } else if(tag.equals("item")){
            item = new Item();
        } else if(tag.equals("bstopid")){
            bstopid = true;
        } else if(tag.equals("bstopnm")){
            bstopnm = true;
        } else if(tag.equals("gpsx")){
            gpsx = true;
        } else if(tag.equals("gpsy")){
            gpsy = true;
        } else if(tag.equals("stoptype")){
            stoptype = true;
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length);
        if(bstopid){
            item.setBstopid(Integer.parseInt(s));
        } else if(bstopnm){
            item.setBstopnm(s);
        } else if(arsno){
            item.setArsno(s);
        } else if(gpsx){
            item.setGpsx(Double.parseDouble(s));
        } else if(gpsy){
            item.setGpsy(Double.parseDouble(s));
        } else if(stoptype){
            item.setStoptype(s);
        }
    }

    public void endElement(String uri, String localName, String tag) throws SAXException {
        if(tag.equals("item")){
            itemList.add(item);
        } else if(tag.equals("bstopid")){
            bstopid = false;
        } else if(tag.equals("bstopnm")){
            bstopnm = false;
        } else if(tag.equals("gpsx")){
            gpsx = false;
        } else if(tag.equals("gpsy")){
            gpsy = false;
        } else if(tag.equals("stoptype")){
            stoptype = false;
        }
    }
}
