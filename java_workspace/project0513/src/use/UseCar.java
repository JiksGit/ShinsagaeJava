/* 자동차 클래스로부터 인스턴스 1개를 생성하여
자동차의 핸들의 색상, 바퀴의 브랜드명,
문짝의 열기 기능을 호출..
*/

package use;

import use.Car;

class UseCar{
	public static void main(String[] args) 
	{	
		Car car = new Car();
		
		car.door.open();
		car.wheel.roll();
		car.handle.rotate();
		car.door.close();
	}
}