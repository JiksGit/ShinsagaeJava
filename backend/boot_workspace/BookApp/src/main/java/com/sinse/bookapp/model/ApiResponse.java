package com.sinse.bookapp.model;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {

    private Header header;
    private List<Body> body;

    @Data
    public static class Body {
        private String bookKey;       // 책 레코드 키
        private String speciesKey;    // 종 레코드 키 (오타: sepeciesKey → speciesKey)
        private String libName;       // 소장 도서관(이름) (오타: libNmae → libName)
        private String manageCode;    // 소장도서관(관리구분)
        private String regNo;         // 등록번호
        private String controlNo;     // 제어번호
        private String callNo;        // 청구기호
        private String shelfLocName;  // 서가 위치
        private String title;         // 표제
        private int vol;              // 권차
        private String volTitle;      // 권차 제목
        private String author;        // 저작자
        private String publisher;     // 발행자
        private int pubYear;          // 발행년도
        private boolean appendixYn;   // 부록 여부
        private String isbn;          // ISBN (int → String 변경)
        private int rnum;             // 순번
    }

    @Data
    public static class Header {
        private String title;
        private String manageCd;
        private int numOfRows;
        private int pageNo;
    }
}
