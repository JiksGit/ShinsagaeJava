class StringTest2{
	public static void main(String[] args){
		/* String 불변의 특징이 있다.
		즉 변경될 수 없다.. immutable */
		String x = "korea";
		x = "korea fighting";
		System.out.println(x);
	}
}