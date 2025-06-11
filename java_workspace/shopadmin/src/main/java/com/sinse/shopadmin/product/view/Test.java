package com.sinse.shopadmin.product.view;

public class Test {

	public static void main(String[] args) {
//		for(int i =0 ; i< 5; i++) {
//			System.out.println(System.currentTimeMillis());
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}			
//		}
//		
		String path = "c://test/babo/mario.test.jpg";
		
		// 1) 가장 마지막 점의 위치를 알아낸다.
		int firstSlash = path.lastIndexOf("/");
		int lastPoint = path.lastIndexOf(".");
		
		// 2) 가장 마지막 점의 위치 바로 다음 위치부터~ 가장 마지막 문자열 까지 추출한다.
		String folder = path.substring(0, firstSlash);
		String fileName = path.substring(firstSlash + 1);
		String fileEx = path.substring(lastPoint + 1);
		
		System.out.println(folder);
		System.out.println(fileName);
		System.out.println(fileEx);
		
		String v = "a100";
		String v2 = "100";
		
		try {
			Integer.parseInt(v);
		} catch(NumberFormatException e) {
			System.out.println("올바른 정수값이 아니네요");
			e.printStackTrace();
		}
	}
}
