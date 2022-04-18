package Sixthlecture;

import FifthLecture.CarClass;


public class AnotherSubCar extends CarClass implements CarInterface {
	public AnotherSubCar() {
		super();	
	}

	public AnotherSubCar(String carName){
		super(carName);	
	}
	
	public  AnotherSubCar(String manu,String model,String key,int engineCC,float fuel) {
		super(manu, model, key, engineCC, fuel);
	}
	
	public AnotherSubCar(String manu,String model,String key,int engineCC,float fuel,int year) {
		super(manu, model, key, engineCC, fuel, year);
	}

	
	@Override
	public void driveCar(int drivindTimeInSecond) {
		// TODO Auto-generated method stub
		runForSeconds(drivindTimeInSecond);
		
	}

	@Override
	public void addAmountOfFuel(int amount) {
		// TODO Auto-generated method stub
		addFuel(amount);
		
	}

	@Override
	public int getManufacturingYear() {
		// TODO Auto-generated method stub
		return manufacturingYear;
	}

	@Override
	public String getCarModelName() {
		// TODO Auto-generated method stub
		return getModelname();
	}
	
	
	
}
