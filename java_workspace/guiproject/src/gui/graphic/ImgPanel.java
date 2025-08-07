/*
컴포넌트 중 주로 컨테이너형은 아무것도 그려지지 않은 투명도화지 
같기 때문에 개발자가 커스터마이징 하기에 너무 좋다
JPanel, Canvas 등.. JFrame 가능은 하다
*/
package gui.graphic;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Font;

public class ImgPanel extends JPanel{
	// 이미지를 얻는 것은 개발자의 능력 밖이므로, 시스템상의 이미지를 대신 구해주는
	// 객체를 통해 추상클래스인 Image 객체의 인스턴스를 얻어와 보자
	Toolkit kit = Toolkit.getDefaultToolkit(); // 이미지를 우리 대신 얻어옴
	Image image; // 추상 클래스이므로, 툴킷으로부터 얻어오자
	
	public ImgPanel(){
		setBackground(Color.YELLOW);	
		
		// 그림을 그리기 전에, 이미지를 먼저 얻어다 놓자
		image = kit.getImage("C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/noteImg.png");
		
		// 270 x 350
		setPreferredSize(new Dimension(270,350));
	}
	
	// 패널이 보유한 그리기 메서드를 재정의한다
	// 붓에 해당됨
	public void paint(Graphics g){
		g.drawImage(image, 0, 0, this);
		// 팔레트 색상 선택
		g.setColor(Color.RED);
		
		g.drawLine(100,0, 200, 200); // 직선 연결
		g.drawLine(150,50, 200, 200); 
		g.drawLine(50,150, 200, 200); 
		g.drawOval(100,100,150,150); // 원그리기
		
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		g.drawString("I'm graphic text", 50, 170); // 글씨 그리기 
		
		g.drawRect(150,200,100,100);
	}
}
