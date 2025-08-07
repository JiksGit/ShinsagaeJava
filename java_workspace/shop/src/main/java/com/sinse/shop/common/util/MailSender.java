package com.sinse.shop.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.sinse.shop.common.exception.EmailException;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

// 이메일 보내주는 객체
public class MailSender {
	
	String account_user = "2wlrlwkd@gmail.com"; // 구글의 이메일 계정 입력
	String app_pwd="mgvg sowq iitb bxal";
	Session session;
	
	public MailSender() {
	
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		
		// TLS는 (Transport Layer Security) 데이터를 암호화해서 안전하게 전송하는 통신 프로토콜
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com"); // 구글 보내는 메일 서버
		props.put("mail.smtp", "587");
		
		session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account_user, app_pwd);
			}
		});
	}
	
	public void send(String targetMail, String title, String content) throws EmailException{		

		
		try {
			// 메일의 내용 구성하기
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(account_user)); // 보내는 사람 메일
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetMail)); // 받을 사람
			message.setSubject(title);
			message.setText(content);
			
			// 메일 전송
			Transport.send(message);
		} catch(AddressException e) {
			e.printStackTrace();			
			throw new EmailException("메일 발송 실패", e);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new EmailException("메일 발송 실패", e);
		} 
	}
	
	public void sendHtml(String targetMail, String title, String content) throws EmailException {
		
		Message message = new MimeMessage(session);
		FileInputStream fis = null;
		BufferedReader buffr = null;
		StringBuffer sb = new StringBuffer();
		
		try {
			fis = new FileInputStream("C:/lecture_workspace/back_workspace/java_workspace/shop/data/mailform.html");
			buffr = new BufferedReader(new InputStreamReader(fis));
			while(true) {
				try {
					String tag = null;
					tag = buffr.readLine(); // 한줄씩 읽기
					if(tag == null) break;
					sb.append(tag); // 한줄씩 읽어들인 문자열을 누적
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(buffr != null) {
				try {
					buffr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			message.setFrom(new InternetAddress(account_user));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(targetMail)); // 받을 사람
			message.setSubject(title);		
			
//			StringBuffer tag = new StringBuffer();
//			tag.append("<h1>가입을 축하드립니다</h1>");
//			tag.append("<p>본 메일은 회원가입 완료 시 보내지는 자동 메일입니다</p>");
//			
			message.setContent(sb.toString(), "text/html; charset=utf-8");
			
			Transport.send(message);
			
		} catch(AddressException e) {
			e.printStackTrace();			
			throw new EmailException("메일 발송 실패", e);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new EmailException("메일 발송 실패", e);
		} 
	}
}






