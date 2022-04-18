package Sixthlecture;

import FifthLecture.*;
import TestPackage.carClass;

public class SixthLecture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CarClass aCar=new CarClass("MITSUBISHI", "lancer", "poiu", 2500 , 15, 2013);
		SubCarClass aSubCar=new SubCarClass("NISSAN", "350z", "zxcv", 4000, 30, 2011, 12, "Grey");
		AnotherSubCar sixthLectureCar=new AnotherSubCar("PORSCHE", "CareeraGT", "jahdy23gi", 4000, 40, 2012);
		
		
		aSubCar.manufacturerName="Mercedes Benz";
		aSubCar.printAllInfo();
		aCar.printAllInfo();
		sixthLectureCar.printAllInfo();
		sixthLectureCar.addAmountOfFuel(12);
		System.out.println("\n\nFuel :"+sixthLectureCar.presentAmountOfFuel());
		System.out.println("MODEL NAME :"+sixthLectureCar.getCarModelName());
		System.out.println("Year :"+sixthLectureCar.getManufacturingYear());
		
	}

}
