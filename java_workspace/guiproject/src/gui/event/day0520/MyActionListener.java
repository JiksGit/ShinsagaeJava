package gui.event.day0520;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class MyActionListener implements ActionListener{
	
	Button bt1;
	Button bt2;
	
	// 메서드를 통해 다른 클래스에 존재하던 버튼들을 전달받음 (메서드 주입 = method injection)
	public MyActionListener(Button bt1, Button bt2){
		this.bt1 = bt1; // this는 MyActionListener
		this.bt2 = bt2;
	}
	
	// 사용자가 액션 이벤트를 일으키면 OS로부터 이벤트 정보를 받은 JVM은
	// 해당 이벤트 정보를 알맞는 이벤트 객체로 인스턴스화 시킨다.
	// 그리고 생성된 이벤트 인스턴스는 재정의 메서드인 actionPerformed 메서드로
	// 전달해준다. 개발자는 자신이 알고싶은 이벤트 정보를 ActionEvent로 부터
	// 모든 것을 알아낼 수 있다.
	// js에서의 addEventListener("click", (e)=>{}); e랑 동일하다
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		System.out.println(obj);
		
		if(obj == bt1){
			System.out.println("A를 눌렀어?");	
		} else if(obj == bt2){
			System.out.println("B를 눌렀어?");	
		}
	}
}
