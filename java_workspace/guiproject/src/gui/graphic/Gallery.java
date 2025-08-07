package gui.graphic;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gallery extends JFrame implements ActionListener{
	JPanel p_north;
	JButton bt_prev;
	JButton bt_next;
	JLabel la_title;
	Toolkit kit; // 시스템의 이미지를 개발자 대신 얻어다 주는 객체
	MyCanvas myCanvas;
	// 자바스크립트와는 달리, 대부분의 언어는 배열이 고정 배열이므로 반드시
	// 배열 선언 시 그 크기를 명시해야한다
	Image[] imgArray = new Image[9];
	int index = 0;
	
	public Gallery(){
		p_north = new JPanel();
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		la_title = new JLabel("제목");
		
		bt_prev.addActionListener(this);
		bt_next.addActionListener(this);
		
		kit= Toolkit.getDefaultToolkit(); // 툴킷의 인스턴스 얻기, 클래스메서드
		
		p_north.add(bt_prev);
		p_north.add(la_title);
		p_north.add(bt_next);
		
		add(p_north, BorderLayout.NORTH);
		
		// 초기 이미지 보여주기
		createImage();
		// 패널에 초기에 이미지 1개를 전달하자
		myCanvas = new MyCanvas();
		myCanvas.setImage(imgArray[index]);
		
		add(myCanvas);
		
		setBounds(200, 200, 600, 500);
		setVisible(true);
	}
	
	// 멤버변수로 선언된 이미지 배열에, 이미지 인스턴스 9개를 준비해놓아야
	// 프로그램 가동과 동시에 사용자가 사용할 수 있다
	public void createImage(){
		String[] path = {
			"animal1.jpg",
			"animal2.jpg",
			"animal3.jpg",
			"animal4.jpg",
			"animal5.jpg",
			"animal6.jpg",
			"animal7.jpg",
			"animal8.jpg",
			"animal9.jpg"
		};
		
		for(int i =0; i< path.length; i++){
			// 툴킷으로부터 이미지 인스턴스 9개를 생성하여 image 배열에 넣어주기
			// 주의) 디렉토리 경로 붙히기
			imgArray[i] = kit.getImage("C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/geographic/" + path[i]);		
		}
	}
	
	// 그래픽 프로그래밍에서, 컴포넌트의 약간의 변화라도 생기면 그 그림은 지워지고
	// 다시 그려져야 하는데, 개발자가 처리하는게 아니라, 시스템이 알아서 렌더링을
	// 담당하게 된다.
	// 컴포넌트에 변경이 생기면 다음의 과정을 거쳐서 그래픽이 구현된다
	/*
	[AWT]
	최초 컴포넌트 등장 시 paint()에 의해 보여짐
	사용자가 컴포넌트의 그림에 변화를 주게 되면, jvm은 기존 그림을 지워야하므로
	update() 메서드를 호출하여 그림을 깨끗이 지움. 그리고 내부적으로 paint()
	메서드를 호출하여 변경된 그림을 화면에 보여준다.
	
	[Swing]
	사용자가 컴포넌트에 변화를 주게되면 update() 메서드가 호출되는 것이 아니라
	paintComponent()를 호출하여 화면을 깨끗이 지운다.. 변경된 그림을 다시
	보여주기 위해 스스로 paint() 를 호출
	*/
	public void showImage(int index){
		myCanvas.setImage(imgArray[index]);
		
		// 변경된 이미지를 보기 위해서는 사용자의 윈도우 조작이 아니라,
		// 개발자가 프로그래밍적으로 지우고 다시 그릴 것을 요청해야 한다.
		// 이 때 호출되는 메서드가 바로 repaint() 즉 다시 그려줄 것을 부탁하는
		// 메서드. 개발자가 절대로 paint()를 직접 호출해서는 안된다.
		// 그림은 시스템이 알아서 그리기 때문에
		// repaint() ==> update(AWT)			  ------> paint()
		//              ==> paintComponent(Swing) ---> paint()
		myCanvas.repaint();
	}
	
	public void actionPerformed(ActionEvent e){
		// 이벤트를 일으킨 컴포넌트를 가리켜 이벤트 소스라 한다 
		Object obj = e.getSource();
		
		// 버튼 인스턴스의 주소값만 비교하면 되므로, 굳이 형변환 할 필요 x
		if(obj == bt_prev){
			index = (index > 0) ? index-1 : 0;
			showImage(index);
		} else if(obj == bt_next){
			index = (index < 8) ? index+1 : 8;
			showImage(index);
		}
	}
	
	// 공부 목적 상, 프레임의 그림을 뻇어서 그려보자
	// public void paint(Graphics g){
	// 	System.out.println("나 그려짐");
	// }
	public static void main(String[] args){
		new Gallery();	
	}
}
