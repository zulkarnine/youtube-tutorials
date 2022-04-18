package FifthLecture;


public class learningClass {

	private int aNumber=12;
	private int anotherNumber;
	static private CarClass ourCar=new CarClass();
	static private CarClass ourAnotherCar;
	public static void main(String[] args) {
		
		CarClass thirdCar=new CarClass("MAZDA", "Axela", "asdf", 1500, 20);
		SubCarClass ourSubCar=new SubCarClass("BMW", "7 Series", "zzxcz", 3000, 25, 10, "grey");
		
		ourAnotherCar=new CarClass("TOYOTA", "Allion", "qwerty", 10000, 30);
		
		//first car
		ourAnotherCar.printAllInfo();
		
		//2nd car
		thirdCar.printAllInfo();
		//subcar
		ourSubCar.printAllInfo();
//		ourSubCar.
		
	}
	
	

}
