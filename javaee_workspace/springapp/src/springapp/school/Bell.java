package springapp.school;

// 이 클래스는 학생의 모든 행동에 적용될 공통 코드를 공통 관심사로 뺴놓자
// 즉, 하나의 외부 관점(Aspect)로 둔다

public class Bell {

	public void sound() {
		System.out.println("딩  동  댕");
	}
	
}
