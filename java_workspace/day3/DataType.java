class DataType{

	public void main(String[] args){
		// 영미권 위주의 아스키 코드보다 확장된 유니코드 기반
		// char c = '강';
		
		short s = 7;
		byte a = 9;
				byte b = 9; 

		long k = 10;
		
		int x = a+b; // int 보다 작은 자료형 간 연산시, 자동 int 형으로 변환발생
		long y = k+s;
	}
}