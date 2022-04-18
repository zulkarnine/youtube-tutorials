package FifthLecture;

public class CarClass {
	
	//public variables
	public String manufacturerName;
	
	//private variables
	private String modelName;
	private String carKeyValue;
	private float fuelAmount;
	final private float amountOfFuelConsumedPerSecond=0.02f;
	
	//default
	int engineCC;
	
	//protected
	protected int manufacturingYear;
	
	public CarClass() {
		manufacturerName=new String();
		modelName=new String();
		engineCC=1000;
		fuelAmount=0;
		manufacturingYear=0;
		
	}
	public CarClass(String carName){
		manufacturerName=new String(carName);
		modelName=new String();
		engineCC=0;
		fuelAmount=0;
		manufacturingYear=0;
		
	}
	
	public  CarClass(String manu,String model,String key,int engineCC,float fuel) {
		manufacturerName=manu;
		modelName=model;
		carKeyValue=key;
		this.engineCC=engineCC;
		fuelAmount=fuel;
		manufacturingYear=0;
	}
	
	public CarClass(String manu,String model,String key,int engineCC,float fuel,int year) {
		this(manu, model, key, engineCC, fuel);
		manufacturingYear=year;
	}
	
	
	public float presentAmountOfFuel() {
		return fuelAmount;
	}
	
	public String getManufacturerName() {
		return manufacturerName;
	}
	
	public String getModelname() {
		return modelName;
	}
	
	public void addFuel(float fuelAmount) {
		this.fuelAmount+=fuelAmount;
	}
	
	public void runForSeconds(int timeInSecond) {
		float usedFuel=timeInSecond*amountOfFuelConsumedPerSecond;
		fuelAmount-=usedFuel;
	}
	
	public boolean matchPassword(String userInput) {
		return userInput.equals(carKeyValue);
	}
	
	public void printAllInfo() {
		System.out.println("\n\n");
		System.out.println("Manufacturer : "+manufacturerName);
		System.out.println("Model Name : "+getModelname());
		System.out.println("Password Match: "+matchPassword("ahbvjahsb"));
		System.out.println("2nd car CC :"+engineCC);
		System.out.println("Amount of Fuel : "+presentAmountOfFuel()+"L");
		addFuel(2);
		System.out.println("Amount of Fuel after adding 2L : "+presentAmountOfFuel()+"L");
		
	}

}
