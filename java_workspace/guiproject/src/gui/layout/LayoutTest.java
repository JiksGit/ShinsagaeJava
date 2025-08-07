package gui.layout;
/* 

GUI 프로그래밍을 윈도우 프로그래밍이라고도 함 
모든 UI 컴포넌트는 윈도우 안에서 구현되므로..

컴포넌트란? 재사용 가능한 객체 단위(예 - 버튼,체크박스(GUI))
[java 컴포넌트의 유형]
- 남을 포함할 수 있는 유형(컨테이너 형)
예) Frame 
	특징 - 남을 포함하려다 보니, 어떻게 배치할 지를 고민해야함
			따라서 컨테이너 형에는 배치 관리자(Layout Manager)가 지원된다
	배치관리자 종류)
		1) BoarderLayout(동,서,남,북,센터)의 방향을 갖는 배치 - *Default Layout*
			각 방향에 들어가는 컴포넌트는 자신의 크기를 잃어버리고, 확장해버린다.
			따라서 대왕버튼이 만들어졌음
		2) FlowLayout(위치가 결정되지 않고 컨테이너에 따라 흘러다님)
			방향성에 따라 수직방향 흐름, 수평방향 흐름으로 나뉜다
			
		3) GridLayout(행과 열의 배치방식)
			각 행, 열에 배치되는 즉 셀에 들어가는 컴포넌트가 자신의 크기를 잃어버리고
			확장해버림
		
		4) CardLayout(포커의 카드처럼 오직 하나의 카드만 보여짐)
			화면 전환시 사용되는데, 직접 만들어 구현해도 되기때문에 몰라도 된다.

- 남에게 포함 당할 수 있는 유형(비주얼 컴포넌트 형)
예 ) 버튼, 체크박스, 초이스, 윈도우 안에 포함되는 모든 요소


*/
import java.awt.Frame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel; // 눈에 보이지 않음(div와 완전 흡사)

public class LayoutTest{
	public static void main(String[] args) {
		// 윈도우 생성
		Frame frame = new Frame("배치 학습");
		
		frame.setSize(500, 400);  
		// 윈도우는 보이고, 안 보이게 하는 기능이 있기 때문에 디폴트는 눈에 안보임
		
		Panel panel; // 윈도우 안에 소속되는 컨테이너형 컴포넌트
		// 따라서 다른 컴포넌트를 포함할 수 있다. Panel도 컨테이너 형이므로 
		// 배치관리자가 지원되며, 개발자가 지정하지 않으면 디폴트가 FlowLayout
		
		
		
		// 버튼 하나 생성(방향을 지정하지 않으면 default는 센터)
		Button bt_center1 = new Button("CENTER");
		Button bt_center2 = new Button("CENTER");
		Button bt_west = new Button("WEST");
		Button bt_south = new Button("SOUTH");
		bt_center1.setBackground(Color.green);
		bt_center2.setBackground(Color.green);
		bt_west.setBackground(Color.magenta);
		bt_south.setBackground(Color.yellow);

		
		panel = new Panel();
		panel.add(bt_south); // 패널에 버튼 부착!
		
		// 상수는 public static final로 선언되었고, 클래스 소속이므로 인스턴스 생성없이
		// 사용 가능함 따라서.. BorderLayout이 보유한 상수명으로 접근 가능
		frame.add(panel, BorderLayout.SOUTH);
		
		panel = new Panel();

		panel.add(bt_center1);
		panel.add(bt_center2);
		frame.add(panel);

		panel = new Panel();
		panel.add(bt_west);
		frame.add(panel, BorderLayout.WEST);

		frame.setVisible(true);

	}
}
