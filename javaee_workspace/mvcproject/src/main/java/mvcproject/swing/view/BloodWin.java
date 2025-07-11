package mvcproject.swing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//
public class BloodWin extends JFrame{
	
	JComboBox box;
	JButton bt;
	
	public BloodWin() {
		box = new JComboBox<>();
		bt = new JButton("판단 요청");
		
		// style
		box.setPreferredSize(new Dimension(185, 30));
		
		// 데이터 채우기
		box.addItem("A");
		box.addItem("B");
		box.addItem("O");
		box.addItem("AB");
		
		setLayout(new FlowLayout());
		add(box);
		add(bt);
		
		bt.addActionListener((e)->{
			getAdvice();
		});
		
		setSize(200,150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void getAdvice() {
		// 혈액형에 대한 판단
		String blood = (String) box.getSelectedItem();
		String msg = null;
		if(blood.equals("A")){
			msg = "A형";
		} else if(blood.equals("B")){
			msg = "B형";
		} else if(blood.equals("O")){
			msg = "O형";
		} else if(blood.equals("AB")){
			msg = "AB형";
		}
		
		JOptionPane.showMessageDialog(this, msg);
	}

//	public static void main(String[] args) {
//		new BloodWin();
//	}
}
