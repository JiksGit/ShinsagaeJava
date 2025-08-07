package com.sinse.mvcapp.color.model;

/*
 * MVC 패턴에 의해, 디자인 영역과 로직 및 데이터 영역 분리시켜야 유지보수성이 높아지기 때문에
 */
public class BloodManager {
	
	public String getAdvice(String blood) {
		String msg = "";
		if(blood.equals("A")){
			msg = "A형";
		} else if(blood.equals("B")){
			msg = "B형";
		} else if(blood.equals("O")){
			msg = "O형";
		} else if(blood.equals("AB")){
			msg = "AB형";
		}
		return msg;
	}
}
