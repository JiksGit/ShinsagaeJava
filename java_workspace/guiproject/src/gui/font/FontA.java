package gui.font;

import java.awt.TextArea;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FontA extends Frame implements ActionListener{

	TextField t_size;
	Button bt_check;
	Panel panel;
	TextA textA;
	
	public FontA(TextA textA){
		t_size = new TextField(30);
		bt_check = new Button("입력");
		this.textA = textA;
		
		panel = new Panel();
		panel.add(t_size);
		panel.add(t_color);
		
		add(panel);
		panel = new Panel();
		panel.add(bt_check);
		add(panel, BorderLayout.SOUTH);
		
		setSize(300, 400);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
	}
	
}
