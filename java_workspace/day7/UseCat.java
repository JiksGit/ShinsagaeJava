/* 실행부를 두기 위한 클래스*/
class UseCat
{
	public static void main(String[] args) 
	{
		new Cat(); // Cat 클래스에 생성자가 눈에 보이지않더라도 디폴트 생성자에 의해 컴파일 에러 x
		System.out.println("Hi!");
	}
}
