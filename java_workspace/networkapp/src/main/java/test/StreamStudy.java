package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StreamStudy {

	String path = "C:/public/data.txt";
	
	// 바이트 기반의 스트림으로 읽기 , 1byte 바이트씩 처리하는 스트림, 영문 안깨짐, 한글 깨짐..
	public void readByte() {
		// 문서 파일을 읽고 그 내용을 출력!
		File file = new File(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			while(true) {
				int data = -1;
				data = fis.read();
				if(data == -1) break;
				System.out.print((char)data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 문자 기반의 스트림으로 읽기
	public void readReader() {
		FileInputStream fis;
		InputStreamReader reader;
		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);
			
			// 주의) 문자기반 스트림은 데이터를 읽어들일 때 1문자씩 읽어들임
			// 오해말기) 한글이 깨지지 는다고 하여 2byte씩 읽어들이는거 아님, 2byte를 묶어서 
			//            문자로 해석할 수 있는 능력이 있는 것 뿐임
			while(true) {
				int data = -1;
				try {
					data = reader.read(); // 영문이어도 한 자, 한글이어도 한 자 입력
					if(data == -1) break;
					System.out.println((char)data);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// 버퍼 기반의 스트림으로 읽기
	public void readBuffer() {
		FileInputStream fis = null;
		InputStreamReader reader = null;
		BufferedReader buffr = null; // 한글까지 깨지지 않으면서, 한자씩 읽는 것이 아니라
												// 줄바꿈문자를 만날때까지 읽지 않고, 버퍼에 문자를 모음
		
		try {
			fis = new FileInputStream(path);
			reader = new InputStreamReader(fis);
			buffr = new BufferedReader(reader);
			
			while(true) {
				String data = null;
				data= buffr.readLine();
				if(data == null) break;
				System.out.println(data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		StreamStudy ss = new StreamStudy();
		ss.readBuffer();
	}
}
