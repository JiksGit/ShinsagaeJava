package backup;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoGUIServer extends JFrame{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	
	public EchoGUIServer() {
		p_north = new JPanel();
		t_port = new JTextField(8);
		bt = new JButton("서버 가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);
	
		setBounds(200, 100, 300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new EchoGUIServer();
	}
	
}