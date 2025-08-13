package com.sinse.yacksuter.model;

import lombok.Data;

import java.util.List;

@Data
public class Yacksu {

    private List<Body> body;
    private Header header;

    @Data
    public static class Header{
        private String resultCode;
        private String resultMsg;
        private int pageSize;
        private int startPage;
        private int totalCount;
        private String around;
        private int bcode;
        private String groupCode;
        private String ingredient;
        private String location;
        private String title;
        private int waterBase;
        private String waterType;
    }

    @Data
    public static class Body{
        private int pageIndex;
        private double posx;
        private double posy;
    }
}
