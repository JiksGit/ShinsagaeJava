package test;

/*
GridLayout : 행과 열(층수 호수)을 지원하는 배치방법
*/
import java.awt.*;

class GridTest {
	public static void main(String[] args) {
		Frame f = new Frame("그리드 배치");
		f.setLayout(new GridLayout(3,4));
		
		for(int i=1; i<= 20; i++){
			for(int j =1; j<= 5; j++){
				Button bt = new Button(i+"층 " + j+"호");
				bt.setBackground(Color.YELLOW);
				f.add(bt);
			}
		}
		
		f.setSize(300, 400);
		f.setVisible(true);
	}
}
