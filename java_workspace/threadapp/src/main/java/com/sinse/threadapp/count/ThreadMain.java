package com.sinse.threadapp.count;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sinse.threadapp.ani.ProgressThread;

public class ThreadMain extends JFrame{

	JTextArea t_left;
	JTextArea t_right;
	
	JPanel panel;
	
	JButton bt;
	
	// 독립적으로 실행할 단위인 쓰레드 2개
	CountThread t1;
	CountThread t2;
	
	public ThreadMain() {
		t_left = new JTextArea();
		t_right = new JTextArea();
		panel = new JPanel();
		bt = new JButton("클릭");
		
		// 스타일
		
		Dimension d = new Dimension(250, 250);
		t_left.setPreferredSize(d);
		t_right.setPreferredSize(d);
		
		setLayout(new FlowLayout());
		
		// 부착
		panel.add(bt, BorderLayout.NORTH);
		
		add(panel);
		
		add(t_left);
		add(t_right);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t1 = new CountThread(t_left, 1000);
				t2 = new CountThread(t_right, 500);
				
				t1.start();
				t2.start();
			}
		});
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ThreadMain();

	}

}
