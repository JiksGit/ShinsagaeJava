package gui.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Config extends JFrame implements ActionListener{
	JTextField t_size;
	JButton bt;
	// has a 관계는 멤버변수로 보유한 관계를 의미한다
	MyWin myWin;
	
	// 외부에서 MyWin을 전달받는다. 이 생성자 함수를 호출하는 자는
	// 주소값에 의한 생성자 호출을 시도해야한다. (Call by Reference)
	public Config(MyWin myWin){
		t_size = new JTextField(20);
		bt = new JButton("설정 적용");
		this.myWin = myWin;
		
		setLayout(new FlowLayout()); // 컴포넌트들이 자신의 본연의 크기 유지
		
		add(t_size);
		add(bt);
		
		// 버튼과 리스너 연결
		bt.addActionListener(this);
		
		setBounds(1100, 350, 300, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		//MyWin이 보유한 area의 폰트의 크기를 설정해주자
		//단 폰트의 크기값은 나의 TextField로 부터 얻은 값이다
		
		//java Wrapper 클래스가 있음
		/*
		자바의 기본 자료형은 3가지
		기본 자료형과 객체자료형간 변환이 가능하도록 지원되는 객체들이 있는데,
		이런 객체들을 가리켜 래퍼클래스 Integer (int x = 3)
		지원되는 이유? 기본 자료형으로는 할 수 없었던 더욱 많은 일을 하기 위해서
		Integer.parseInt("45"); --> 45
		1) 숫자
			정수: byte < short < int < long
				   Byte   Short   Integer Long
			실수: float < double 
				   Float   Double
		2) 문자 : char
					Character
		3) 논리값 : boolean
					  Boolean
		
		*/
		int size = Integer.parseInt(t_size.getText());
		myWin.area.setFont(new Font(null,0, size));
	}
}
