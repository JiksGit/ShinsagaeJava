/* 실행중인 java 프로그램에서 디스크의 파일을 접근하여
데이터를 읽고, 프로그램으로 불러들여 출력해보자 */
package gui.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileLoader{
	public static void main(String[] args) throws FileNotFoundException{
		// 실행중인 프로그램이 파일 등의 자원의 데이터를
		// 읽어들이기 위해서는 스트림 객체가 필요하다.
		/*
		Stream 이란? 현실에서의 물줄기, 물의 흐름을 의미
						 전산에서는 그 대상이 물이 아닌 데이터
		흐름의 방향은 2가지 유형
		IO(입출력)
		1) 실행중인 프로세스로 들어오는 유형이 Input(입력)
		2) 실행중인 프로세스에서 나가는 유형이 Output(출력)
		*/
		// 파일을 대상으로 한 입력 객체(파일을 읽어들일 수 있는 객체)
		
		// 문법상은 문제없지만, 프로그램 코드 상의 문제 외의 문제 떄문에
		// 프로그램이 정상 수행이 될 수 없는 상황이 될 수 있음
		// 따라서 컴파일러 차원에서 컴파일 거부 
		FileInputStream fis = null;
		try{
			fis = new FileInputStream("C:/lecture_workspace/back_workspace/java_workspace/guiproject/res/memo.txt");
			int data;
			
			while((data = fis.read()) != -1){
					System.out.print((char)data);
			}
			//while(true){
			//	data=fis.read();
			//	if(data==-1) break;
			//	System.out.println((char)data);
			//}
			// File을 찾더라도 file이 지워질 수 있기때문에, IOException 예외처리 필요
		} catch(FileNotFoundException e){ // catch 문의 소괄호안에 error의 원인이 되는 객체의 인스턴스를 생성하여 전달
			// 개발자는 오류 객체를 분석할 수 있음.
			// 만약 파일이 없다면, 파일을 복구하지는 못하므로, 원인등을 알려주거나
			// 로그를 남기는 등의 처리.
			System.out.println("파일을 찾을 수 없습니다.");
		} catch(IOException e){
			System.out.println("입출력 예외 발생");
		} finally { // try, catch 문을 수행한 후 반드시 거쳐나가는 영역
			// DB와 스트림은 반드시 닫아야 한다
			if(fis != null){
				try{
					fis.close(); // 스트림 닫기	
				} catch (IOException e){
					e.printStackTrace(); // 일반 유저가 아닌 개발자를 위한 로그 출력
				}
			}
		}
	}
}