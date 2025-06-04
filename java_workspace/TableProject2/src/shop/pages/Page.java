package shop.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Page extends JPanel{
	JLabel la_title;
	
	public Page(String text) {
		la_title = new JLabel(text);
		la_title.setFont(new Font("Gulim", Font.BOLD, 30));
		
		add(la_title);
		setPreferredSize(new Dimension(800, 650));
		setVisible(false);
	}
}
