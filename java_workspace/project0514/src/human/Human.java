/* 
클래스 정의 시 중복되는 코드의 재사용을 위해서는 상속이라는 클래스
정의법을 이용할 수 있다.
*/

package human;

/*
parent : GUI 프로그래밍에서 컨테이너 <--> child
super : 상속 관계에서 부모 객체  <--> sub
*/

public class Human{
	private String skinColor;
	int leg = 2;
	
	public void intellecThink(){
		System.out.println("지적인 사고력");	
	}
}