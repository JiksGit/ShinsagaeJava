package gui.graphic;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class MoveTest extends JFrame implements ActionListener{
	
	JButton bt;
	JPanel p_north;
	MovePanel p_center;
	int index_x = 100;
	int index_y = 100;
	
	public MoveTest(){
		bt = new JButton("버튼");
		p_north = new JPanel();
		
		bt.addActionListener(this);
		p_north.add(bt);
		
		p_center = new MovePanel();
		
		add(p_north, BorderLayout.NORTH);
		add(p_center);
		
		setBounds(600, 350, 600,650);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		// MovePanel의 빨간색 원을 이동시키자
		p_center.move();
		p_center.repaint();
	}
	
	public static void main(String[] args) 
	{
		new MoveTest();
	}
}
