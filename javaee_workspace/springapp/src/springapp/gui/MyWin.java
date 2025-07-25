package springapp.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * 스프링은 웹 뿐 아니라, 독립실행형 즉 데스크탑 기반 애플리케이션, 안드로이드 개발에 적용 가능
 */
public class MyWin extends JFrame{

	private JComponent name;
	private JComponent email;
	private JComponent bt;
	
	// 외부 환경인 스프링으로부터, 필요한 빈들을 주입 받으려면
	// 개발자는 두 가지 중 선택할 수 있다.
	// 1) 생성자 주입
	// 2) setter 주입(일반 메서드)
	public MyWin(JComponent name, JComponent email, JComponent bt) {
		this.name = name;
		this.email = email;
		this.bt = bt;
		
		setLayout(new FlowLayout());
		add(name);
		add(email);
		add(bt);
		
		setSize(200,150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	public void setName(JComponent name) {
		this.name = name;
	}


	public void setEmail(JComponent email) {
		this.email = email;
	}

	public void setBt(JComponent bt) {
		this.bt = bt;
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		context.getBean(MyWin.class);
	}
	
}
