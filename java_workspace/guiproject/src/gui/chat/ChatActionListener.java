package gui.chat;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.TextField;

class ChatActionListener implements ActionListener{
	Button bt;
	TextField tf;
	
	
	public ChatActionListener (Button bt, TextField tf){
		this.bt = bt;
		this.tf = tf;
	}
	
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		ChatKeyListener key_action = new ChatKeyListener(this.tf);
		// System.out.println(obj);
		
		if(obj == bt || obj == tf){
			ChatB chatb = new ChatB();	
		}
	}
}
