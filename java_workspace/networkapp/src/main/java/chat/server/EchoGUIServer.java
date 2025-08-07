package chat.server;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoGUIServer extends JFrame implements Runnable{
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	ServerSocket server;
	Thread thread; //Runnable은 쓰레드가 아니므로, Runnable을 구현한다고 하여, 이 객체가 쓰레드
	// 형이라고 오해하면 안됨.. 따라서 별도의 Thread 객체를 사용해야함.. 단지 해당 Thread의 run 메서드를 가진것뿐
	
	// 클라이언트와의 대화를 위한 스트림 준비
	BufferedReader buffr;
	BufferedWriter buffw;
	
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
		
		// 가동 버튼에 리스너 연결
		bt.addActionListener(e -> {
			thread = new Thread(EchoGUIServer.this);
			thread.start(); // main 쓰레드 역할이 아닌 JVM, OS에게 역할 부여
		});
	
		setBounds(200, 100, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void startServer() {
		try {
			server = new ServerSocket(Integer.parseInt(t_port.getText()));
			area.append("서버 객체 생성 및 접속 청취중...\n");
			
			Socket socket = server.accept(); // 접속자가 있을 때 까지 무한 대기에 빠짐
			String ip = socket.getInetAddress().getHostAddress();
			area.append(ip + "님 접속자 발견... \n");
			
			// 얻어진 소켓으로부터 스트림 두개 뽑자
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				// 클라이언트가 보낸 메시지 듣기				
				String msg = buffr.readLine();
				area.append(msg + "\n"); // 서버에 로그 남기기 .
				buffw.write(msg + "\n"); // 버퍼 기반의 스트림이므로, 문자열의 끝을 알려주지 않으면
													// 무한 대기에 빠짐...
				buffw.flush();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 우리가 알고있던 소위 실행부라 불렀던 대상의 정체가 사실 쓰레드였다..(주의: 일반 쓰레드 아님)
	 프로그램 운영 쓰레드이다. 운영쓰레드의 주 역할은 ( 이벤트 감지, GUI구성, 관리 ... )
	 특히 스마트폰 개발 시, 메인쓰레드 자체를 대기 상태에 넣는 것 자체가 에러.. 금지..
	 */
	public static void main(String[] args) {
		
		new EchoGUIServer();
	}

	public void run() {
		startServer();
	}
	
}