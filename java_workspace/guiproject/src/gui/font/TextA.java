package gui.font;

import java.awt.TextArea;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class TextA extends Frame implements ActionListener{
	
	TextArea area;
	Button bt_open;
	Panel p_south;
	FontA fontA;
	
	public TextA(){
		
		area = new TextArea();
		bt_open = new Button("서식");
		
		area.setBackground(Color.YELLOW);
		
		add(area);
		
		p_south = new Panel();
		
		bt_open.addActionListener(this);
		
		p_south.add(bt_open);
		add(p_south, BorderLayout.SOUTH);
		
		setSize(300, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
		// this란? 인스턴스가 자기 자신을 가리키는 레퍼런스 변수
		fontA = new FontA(this);	
	}
	
	public static void main(String[] args) {
		new TextA();
	}
}
