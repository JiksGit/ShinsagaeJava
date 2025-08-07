//java.exe의 대상이 되려면, 반드시 실행부인 main() 메서드가 존재해야함
class ObjectType{
	public static void main(String[] args){
		//java 에서는 자료형이 총 4개가 지원된다.
		//기본 자료형(문자,숫자,논리값), 객체 자료형도 자료형이다.
		int x= 5;
		
		//고정관념에서 벗어나자, 자바에서는 개발자가 정의한 클래스를 새로운 자료형으로
		//인정해준다. 따라서 아래의 코드에서 변수앞에 선언해야하는 자료형은
		//내가 정의한 Dog형이다.
		Dog dog = new Dog();
		dog.bark();
		
		Bom bom = new Bom();
		bom.bark();
	}
}