package gui.graphic;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

class MovePanel extends JPanel{
	
	private int x;
	private int y;
	
	public void move(){
			x += 10;
			y += 10;
	}
	
	// JPanel의 paint()메서드를 오버라이딩
	public void paint(Graphics g){
		// 채워진 원 그리기
		g.setColor(Color.RED);
		g.fillOval(x, y, 45, 45);
	}
	
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
}
