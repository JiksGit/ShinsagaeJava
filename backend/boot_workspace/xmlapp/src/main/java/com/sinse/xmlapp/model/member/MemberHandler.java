package com.sinse.xmlapp.model.member;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/*
    전통적으로 java언어에서 xml을 파싱하는 방법은 크게 2가지가 있다.
    1) DOM 방식 - 처리가 간단, 하지만 메모리의 모든 요소를 DOM으로 올려놓는 방식이라
                        메모리의 자원을 많이 사용하므로, 특히 스마트폰용 웹을 동시에 지원하는 프로젝트 일 경우
                        사용을 지양함
    2) SAX 방식 - 실행부가 xml문서를 위에서 아래 방향으로 진행하면서, 적절한 이벤트를 일으켜
                        해당 이벤트와 관련된 메서드를 호출하는 방식
                        개발자는 이벤트 발생 시 적절한 타이밍을 놓치면 안됨.. 처리가 까다롭다..
*/
@Slf4j
@Component
public class MemberHandler extends DefaultHandler {

    @Getter
    private List<Member> memberList;

    Member member;

    // 실행부가 어디 태그를 지나가는 지를 알 수 있는 기준 변수
    private boolean isName = false;
    private boolean isAge = false;
    private boolean isJob = false;
    private boolean isTel = false;

    // 문서가 시작될 때
    @Override
    public void startDocument() throws SAXException {
        log.debug("문서가 시작되었네요");
        memberList = new ArrayList<>();
    }

    // 시작 태그를 만났을 때
    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
        log.debug("<"+tag+">");
        if(tag.equals("member")){
            member = new Member();
        } else if (tag.equals("name")){
            isName = true;
        } else if(tag.equals("age")){
            isAge = true;
        } else if(tag.equals("job")){
            isJob = true;
        } else if(tag.equals("tel")){
            isTel = true;
        }
    }


    // 태그와 태그 사이의 컨텐츠를 만났을 때
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content =  new String(ch, start, length);
        log.debug(content);

        if(isName) member.setName(content);
        if(isAge) member.setAge(Integer.parseInt(content));
        if(isJob) member.setJob(content);
        if(isTel) member.setTel(content);
    }

    // 종료 태그를 만났을 때
    @Override
    public void endElement(String uri, String localName, String tag) throws SAXException {
        log.debug("</"+tag+">");

        if(tag.equals("member")){
            memberList.add(member);
        }  else if(tag.equals("name")){
            isName = false;
        }  else if(tag.equals("age")){
            isAge = false;
        }  else if(tag.equals("job")){
            isJob = false;
        }   else if(tag.equals("tel")){
            isTel = false;
        }

    }

    // 문서가 끝날 때
    @Override
    public void endDocument() throws SAXException {
        log.debug("xml 문서 파싱 후 담겨진 회원수는 " + memberList.size());
    }


}
