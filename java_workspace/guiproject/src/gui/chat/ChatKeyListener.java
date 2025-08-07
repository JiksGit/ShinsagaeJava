package gui.chat;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.TextField;

public class ChatKeyListener implements KeyListener{
	TextField tf;
	
	public ChatKeyListener (TextField tf){
		this.tf = tf;	
	}
	
	public void keyTyped(KeyEvent e){};

	// 키보드 눌렀다 뗄 때
	public void keyReleased(KeyEvent e){
	};
		
	public void keyPressed(KeyEvent e){
		System.out.println(e.getSource());
	};
}
