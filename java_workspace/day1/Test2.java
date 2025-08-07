class Test2 
{
	public static void main(String[] args) 
	{
		/*
		java도 기존의 언어의 전통을 이어받았기 때문에,
		기본 자료형은 문자, 숫자, 논리값 3가지이다. */
		// 자바의 문자는 문자와 문자열로 구분.. 
		// 자바의 문자 자료형 char
		char a = 'A'; // 한글자를 문자형이라고 한다.
		// 자바의 문자열 : 두글자 이상의 문자 집합
		String str = "대한민국";
		// 논리값은 js 및 다른 언어와 동일 true/flase
		boolean flag = true; // 주의 : 다른언어에서는 1이 true, 0이 false 
									// but 자바는 안됨! 철저히 true or false 표기
									
		/* java 숫자형은 크게 정수와 소수점을 지원하는 실수로 구분 */
		// 정수는 용량에 따라 byte < short < int < long
		
		// 실수는 float < double
		int x = 76;
		float y= 5.6f; // 자바에서는 개발자가 소수점만 적으면 무조건 double로 생각
		
		System.out.println(str);
	}
}
