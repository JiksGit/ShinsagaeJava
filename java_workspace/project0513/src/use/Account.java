/*
객체지향 언어의 장점
1) 캡슐화
2) 상속
3) 다형성
4) 추상화
*/

package use;

public class Account{
	private  int balance = 500000;
	private String num = "789-124433-23";
	private String bank = "하나은행";
	private String owner = "Adams";
	
	// 클래스 작성 시, 데이터를 보호하고 이 보호된 데이터를 외부에서
	// 사용하게 해주려면, 공개된 메서드를 제공해주어야 하는데, 이러한 클래스
	// 정의 기법을 가리켜 은닉화 = encapsulation
	
	// 데이터에 대해 읽기, 쓰기가 가능하도록 메서드 정의
	public int getBalance(){
		return balance;	
	}
	public String getNum(){
		return num;	
	}
	public String getBank(){
		return bank;	
	}
	public String getOwner(){
		return owner;	
	}
	public void setBalance(int balance){
		this.balance = balance;	
	}
	public void setNum(String num){
		this.num = num;	
	}
	public void setBank(int bank){
		this.bank = bank;	
	}
	public void setOwner(int owner){
		this.owner = owner;	
	}
}
