package test;

public class Mouse
{
	private String color;
	
	private int age;
	
	/**
	쥐를 왼쪽으로 이동시키고 싶으면 이 메서드 호출
	*/
	public void Left_move(){
		System.out.println("좌로 이동");	
	}
	
	/**
	쥐를 오른쪽으로 이동시키고 싶으면 이 메서드 호출
	*/
	public void Right_move(){
		System.out.println("우로 이동");	
	}
	
	
	/**
	쥐의 나이를 세팅하고 싶다면 이 메서드 호출
	*/
	public void setAge(int age){
		this.age = age;
	}
}
