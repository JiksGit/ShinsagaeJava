package shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import shop.pages.Home;
import shop.pages.Mypage;
import shop.pages.Page;
import shop.pages.Product;
import shop.pages.CS;
import util.ImageUtil;

/* 화면을 4개 보유한 쇼핑몰 애플리케이션의 화면 전환 처리 방법 */
public class ShopMain extends JFrame implements ActionListener{
	
	JPanel p_north; // 북쪽 패널
	JButton bt_home;
	JButton bt_product;
	JButton bt_mypage;
	JButton bt_cs;
	
	JPanel p_center; // 프로그램 가동과 동시에 이 패널에는 앞으로 사용하게 될 페이지들을 미리
							// 부착하게 될 예정
	
	/*
	 * URI : 자원을 표현하는 방법 (표준이 정해짐)
	 * URL : URI 중에서 해당 자원의 위치를 표현함
	 */
	
	ImageUtil imageUtil;
	
	Home home;
	Mypage mypage;
	Product product;
	CS cs;
	
	Page[] pageArray = new Page[4];
	
	public static final int HOME = 0;
	public static final int PRODUCT = 1;
	public static final int MYPAGE = 2;
	public static final int CS = 3;
	
	public ShopMain() {		
		imageUtil = new ImageUtil();
		// 생성
		p_north = new JPanel();
		
		bt_home = new JButton(imageUtil.getIcon("home.png", 35,35));
		bt_mypage = new JButton(imageUtil.getIcon("mypage.png", 35,35));
		bt_product = new JButton(imageUtil.getIcon("product.png", 35,35));
		bt_cs = new JButton(imageUtil.getIcon("write.png", 35,35));
		
		// 개발자가 버튼에 추가적인 값을 심을 수 있다
		bt_home.putClientProperty("id", HOME);
		bt_product.putClientProperty("id", PRODUCT);
		bt_mypage.putClientProperty("id", MYPAGE);
		bt_cs.putClientProperty("id", CS);
		
		p_center = new JPanel();
		
		home = new Home();
		mypage = new Mypage();
		product = new Product();
		cs = new CS();
		
		// 스타일
		p_north.setPreferredSize(new Dimension(800, 50));
		p_north.setBackground(Color.yellow);
		
		Dimension d = new Dimension(40,35);
		bt_home.setPreferredSize(d);
		bt_product.setPreferredSize(d);
		bt_mypage.setPreferredSize(d);
		bt_cs.setPreferredSize(d);
		
		
		// 조립
		p_north.add(bt_home);
		p_north.add(bt_product);
		p_north.add(bt_mypage);
		p_north.add(bt_cs);
		
		pageArray[0] = home;
		pageArray[1] = product;
		pageArray[2] = mypage;
		pageArray[3] = cs;

		for(int i=0; i<pageArray.length; i++) {
			p_center.add(pageArray[i]);
		}
		
		
		bt_home.addActionListener(this);
		bt_product.addActionListener(this);
		bt_mypage.addActionListener(this);
		bt_cs.addActionListener(this);
		
		add(p_north, BorderLayout.NORTH);
		add(p_center); // 프레임의 센터에 패널 부착
		
		setSize(800, 650);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 컴퓨터 화면 정 가운데 위치
	
	}
	
	//원하는 페이지만 보여지게 처리하는 메서드
	public void showPage(int target) {
		for(int i =0; i< pageArray.length; i++) {
			pageArray[i].setVisible((i==target)?true : false);
			/*
			if(i == target) {
				pageArray[i].setVisible(true);
			}else {
				pageArray[i].setVisible(false);
			}
			*/
		}
	}
	
	public static void main(String[] args) {
		new ShopMain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			JButton obj = (JButton)e.getSource();
		
			int id = (int) obj.getClientProperty("id");
			System.out.println("당신의 상수값" + id);
			showPage(id);
	}
}
