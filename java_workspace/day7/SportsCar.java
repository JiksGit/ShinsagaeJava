class  SportsCar
{
	String color =  "None";
	public SportsCar(String color){
			this.color = color;
	}
	public void setColor(String color){
		this.color = color;	
	}
	public static void main(String[] args) 
	{
		SportsCar car = new SportsCar("black");
		System.out.println(car.color);
		car.setColor("pink");
		System.out.println(car.color);
		car.setColor("blue");
		System.out.println(car.color);
	}
}
