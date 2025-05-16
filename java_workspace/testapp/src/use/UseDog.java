package use; // 개발자가 패키지를 선언하면 javac -d 옵션 사용시
				   // 선언한 패키지에 해당하는 디렉토리 자동 생성해준다.

// classpath : C:\lecture_workspace\back_workspace\java_workspace\testapp\bin 
// 패키지 명 앞에 부분선언, .java파일이 있는 src 패키지가 아닌, .class파일이 있는 bin패키지로 임포트
import animal.Dog; // classpath 환경변수를 기준으로.. 그 밑의 animal 밑의
							// Dog.class를 임포트한다.

class UseDog 
{
	public static void main(String[] args) 
	{
		Dog d = new Dog();
		d.bark();
	}
}
