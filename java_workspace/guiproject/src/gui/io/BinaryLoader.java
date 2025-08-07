/*
메모장과 같은 텍스트 파일이 아닌, 이미지, 동영상 등의 바이너리 파일을
읽어보자
*/

package gui.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class BinaryLoader {
	FileInputStream fis; // 파일을 대상으로 한 입력 스트림
	FileOutputStream fos; // 파일을 대상으로 한 출력 스트림
	String name = "C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/noteImg.png";
	String target = "C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/noteImg_copy.png";
	
	public BinaryLoader(){
		//  컴파일 시 예외처리를 강요하는 예외 방식을 가리켜 강제 예외라 함
		try {
			fis = new FileInputStream(name);
			fos = new FileOutputStream(target);
			
			int data;
			while(true){
				data = fis.read(); // 1byte 읽어들임. 호출 시마다 다음 데이터로 넘어감
				if(data == -1) break;
				System.out.println(data);
				fos.write(data);
			}
		} catch(FileNotFoundException e){
			System.out.println("파일이 음서예");	
		} catch(IOException e){
			System.out.println("입출력 예외발생");	
		}finally{
			if (fos != null) {
				try{
					fos.close();
				} catch(IOException e){
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try{
					fis.close();
				} catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}	
	
	public static void main(String[] args)  throws FileNotFoundException {
		new BinaryLoader();
	}
}
