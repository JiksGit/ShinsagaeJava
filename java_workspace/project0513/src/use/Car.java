/*
최대한, 현실을 반영하여 자동차를 정의해보자
조건1) 자동차의 핸들이 있어야함
조건2) 자동차의 바퀴도 있어야함
조건3) 자동차의 문짝도 있어야함
*/
package use;

import use.*;

public class Car{
	private int price;
	private String name;
	Handle handle; 
	Wheel wheel;
	Door door;
	
	// 생성자는 사물을 태어나게 하는 시점에, 초기화에 관여하므로
	// 특히 has a 관계에 있는 객체의 인스턴스를 생성할 때 아주 유용함
	public Car(){
			price= 7500;
			name="genesis g80";
			handle = new Handle();
			wheel = new Wheel();
			door = new Door();
	}

	
}
