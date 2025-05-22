package gui.chat;

import java.awt.TextArea;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class ChatB extends Frame implements KeyListener{
	
	TextArea area;
	TextField t_input;
	Panel p_south;
	ChatA chatA;
	
	public ChatB(ChatA chatA){
		area = new TextArea();
		t_input = new TextField(30);
		this.chatA = chatA;
		
		area.setBackground(Color.YELLOW);
		
		add(area);
		
		Panel p_south = new Panel();
		
		t_input.addKeyListener(this);
		
		p_south.add(t_input);
		
		
		add(p_south,BorderLayout.SOUTH);
		
		setSize(300, 400);
		setVisible(true);
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
			chatA.area.append(this.getClass().getName() + " : "+msg + "\n");
			this.area.append(msg+ "\n");
			
			t_input.setText("");
			
		}
	};
		
	public void keyPressed(KeyEvent e){ //keydown
	};
}
