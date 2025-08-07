package gui.chat;

import java.awt.TextArea;
import java.awt.Frame;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class ChatA extends Frame implements ActionListener, KeyListener {
	
	TextArea area;
	Button bt_open;
	TextField t_input;
	Panel p_south;
	ChatB chatB;
	
	public ChatA(){
		area = new TextArea();
		bt_open = new Button("열기");
		t_input = new TextField(30);
		
		area.setBackground(Color.YELLOW);
		
		add(area);
	
		bt_open.addActionListener(this);
		t_input.addKeyListener(this);
		
		Panel p_south = new Panel();
		
		p_south.add(t_input);
		p_south.add(bt_open);
		
		add(p_south,BorderLayout.SOUTH);
		
		setSize(300, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
		// this란? 인스턴스가 자기 자신을 가리키는 레퍼런스 변수
		chatB = new ChatB(this);	
	}
	
	// KeyListener의 메서드를 재정의
	public void keyTyped(KeyEvent e){ 
	};

	// 키보드 눌렀다 뗄 때
	public void keyReleased(KeyEvent e){ // keyup
		// System.out.println(e.getKeyCode());
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			// ChatB가 보유한 area의 텍스트값을 채팅값으로 넣자
			String msg = t_input.getText();
			String name = this.getClass().getName();
			chatB.area.append(this.getClass().getName() + " : "+msg + "\n");
			this.area.append(msg+ "\n");
			
			t_input.setText("");
			
		}
	};
		
	public void keyPressed(KeyEvent e){ //keydown
	};
	
	public static void main(String[] args) {
		new ChatA();
	}
}
