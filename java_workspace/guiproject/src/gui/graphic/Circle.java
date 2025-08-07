package gui.graphic;

import java.awt.Graphics;
import java.awt.Color;

public class Circle{
	
	public void paint(Graphics g){
		g.setColor(Color.RED);
		g.drawOval(100,100,150,150); // 원그리기
	}
}
