class Test{
            /* javascript는 개발자가 변수에 할당한 데이터의 종류 판단을, 실행할 때마다 해석.. 
	interpreter 언어(브라우저로 실행할 때마다.. 매번...) - C언어
	장점 - 수시로 데이터를 바꾸면서 그 결과를 확인할 때 
	java는 자료형에 대한 판단을, 실행 전에 문법 검사시에 시도한다.. 그리고 그 결과를
	파일로 저장해놓고, 프로그램을 실행할 때는 두 번 다시 자료형이나, 문법에 대한
	판단을 하지 않는다.. 이러한 방식의 언어를 컴파일 언어 */

	// 자바 클래스를 실행하려면, 반드시 메인함수를 정의해야한다..
	// 이미 정해진 함수명이므로, 절대 다르게 해서는 안됨
   	public static void main (String[] args){
		// console.log() 비슷...
		// System.out.println("자바첫날 첫 클래스 실행");
		for(int i=1; i<= 9; i++){
			for(int j=1; j<=9; j++){
				System.out.print(i+"*" + j +"=" + i*j +"\t");
			}
			System.out.println();
		}
	}
}