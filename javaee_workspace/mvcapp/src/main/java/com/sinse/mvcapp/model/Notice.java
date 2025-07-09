package com.sinse.mvcapp.model;

import lombok.Data;

// Lombok getter/setter 자동 생성
@Data
public class Notice {
	private int notice_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
}
