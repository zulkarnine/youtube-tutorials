package FifthLecture;


public class SubCarClass extends CarClass {
	
	private float chargeInBattery;
	private String colorString;
	
	public SubCarClass() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public SubCarClass(String manu,String model,String key, int Cc,float fl,float charge,String col){
		super(manu, model, key, Cc, fl);
		chargeInBattery=charge;
		colorString=col;


	}
	
	public SubCarClass(String manu,String model,String key, int Cc,float fl,int year,float charge,String col){
		super(manu, model, key, Cc, fl,year);
		
		chargeInBattery=charge;
		colorString=col;

	}
	
	public float getChargeInBattery() {
		return chargeInBattery;
	}
	public void setChargeInBattery(float chargeInBattery) {
		this.chargeInBattery = chargeInBattery;
	}


	public String getColorString() {
		return colorString;
	}
	
	@Override
	public void printAllInfo() {
		super.printAllInfo();
		runForSeconds(60);
		System.out.println("Amount of Fuel after driving for 60s : "+presentAmountOfFuel()+"L");
		System.out.println("Charge in Battery :"+getChargeInBattery());
		System.out.println("Color of car :"+getColorString());
	}
	
}
