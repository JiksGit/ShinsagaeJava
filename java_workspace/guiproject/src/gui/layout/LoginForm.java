package gui.layout.src;

import java.awt.*;

public class LoginForm{
	public static void main(String[] args) {

		// 이 디자인에 사용될 컴포넌트들을 우선 선언.
		Frame frame = null;
		Panel p_center = null;
		Panel p_south = null;
		Label la_id=null, la_pwd=null;
		TextField t_id=null, t_pwd=null;
		Button bt_login=null, bt_join=null;
		
		// 생성
		frame = new Frame("LoginForm");
		p_center = new Panel();
		p_south = new Panel();
		la_id = new Label("ID");
		la_pwd = new Label("Password");
		t_id = new TextField(20); // 생성자의 초기 글자수 너비
		t_pwd = new TextField(20);
		bt_login = new Button("Login");
		bt_join = new Button("Join");
		
		// 조립
		p_center.setLayout(new GridLayout(2,2)); // 2층 2호수 그리드 적용
		p_center.add(la_id);
		p_center.add(t_id);
		p_center.add(la_pwd);
		p_center.add(t_pwd);
		frame.add(p_center, BorderLayout.CENTER); // 디폴트도 센터
		
		p_south.add(bt_login); // 남쪽 패널에 버튼 부착
		p_south.add(bt_join); // 남쪽 패널에 버튼 부착
		
		// 패널을 윈도우의 남쪽에 부착
		frame.add(p_south, BorderLayout.SOUTH);
		
		// 윈도우 설정
		frame.setSize(220, 135);
		
		frame.setVisible(true);
	}
}
