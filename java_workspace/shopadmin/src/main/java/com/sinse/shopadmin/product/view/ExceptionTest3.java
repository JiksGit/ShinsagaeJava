package com.sinse.shopadmin.product.view;

public class ExceptionTest3 {

	public static void main(String[] args) throws NumberConvertFailException{
		
		System.out.println("A");
		
		// throw는 에러 강제 발생
		throw new NumberConvertFailException("내가만든예외");			
		
//		System.out.println("B");
	}
}
