package mvcproject.blood.model;

import javax.swing.JOptionPane;

// MVC에서 Model은 비즈니스 로직이 구현된 코드이므로, 재사용의 대상이된다
// MVC 개발자의 노력이 가장 많이 들어가는 영역
public class BloodManager {

	public String getAdvice(String blood) {
		// 혈액형에 대한 판단
		String msg = null;
		
		if (blood.equals("A")) {
			msg = "A형";
		} else if (blood.equals("B")) {
			msg = "B형";
		} else if (blood.equals("O")) {
			msg = "O형";
		} else if (blood.equals("AB")) {
			msg = "AB형";
		}
		return msg;
	}
}
