package gui.event;

import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import gui.event.day0520.MyActionListener;

public class DoubleButton{

	public static void main(String[] args){
		Frame frame = null;
		Button bt_a = null;
		Button bt_b = null;
		
		frame = new Frame();
		frame.setLayout(new FlowLayout());
		
		bt_a = new Button("A");
		bt_b = new Button("B");
		
		frame.add(bt_a);
		frame.add(bt_b);
		
		MyActionListener my = new MyActionListener(bt_a, bt_b);
		// my.setBtn(bt_a, bt_b);

		bt_a.addActionListener(my);
		bt_b.addActionListener(my);
	
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
}

