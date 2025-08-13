package com.sinse.childrenparkapp.model.park;

import lombok.Data;

import java.util.List;

@Data
public class Park {

    private List<Body> body;
    private Header header;

    @Data
    private static class Header{

    }

    @Data
    private static class Body{

    }

}
