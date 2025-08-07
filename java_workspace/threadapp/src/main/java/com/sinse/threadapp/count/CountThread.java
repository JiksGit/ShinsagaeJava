package com.sinse.threadapp.count;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class CountThread extends Thread{

	JTextArea area;
	int n;
	int speed;
	
	public CountThread(JTextArea area, int speed) {
		this.area = area;
		this.speed = speed;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(speed);
				n++;
				area.setText(String.valueOf(n));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
