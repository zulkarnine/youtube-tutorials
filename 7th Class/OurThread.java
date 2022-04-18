package SeventhLecture;

public class OurThread extends Thread {
	

	//---------------Variables----------------------
	private String threadName;
	private int startingPoint;
	private int counter;
	private int adder;
	private int timeDifference;
	private OurThread dependentThread;
	
	private boolean threadAliveFlag;


	//---------------Constructor----------------------
	
	public OurThread(String threadName, int startingPoint, OurThread dependentThread,
			int adder, int timeDifference, boolean threadAliveFlag) {
		super();
		this.threadName = threadName;
		this.startingPoint = startingPoint;
		this.adder = adder;
		this.timeDifference = timeDifference;
		this.threadAliveFlag = threadAliveFlag;
		
		this.dependentThread=dependentThread;
	}
	

	//---------------run() method----------------------
	
	
	@Override
	public void run() {
		counter=startingPoint;
		
		if (dependentThread!=null) {
			try {
				dependentThread.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
		
		
		while (threadAliveFlag) {
			counter+=adder;
			
			try {
				sleep(timeDifference);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	//--------------StopThread method--------------------------
	
	public void stopThread() {
		threadAliveFlag=false;
	}
	
	
	
	//---------------Getters and Setters----------------------
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public int getStartingPoint() {
		return startingPoint;
	}

	public void setStartingPoint(int startingPoint) {
		this.startingPoint = startingPoint;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getAdder() {
		return adder;
	}

	public void setAdder(int adder) {
		this.adder = adder;
	}

	public int getTimeDifference() {
		return timeDifference;
	}

	public void setTimeDifference(int timeDifference) {
		this.timeDifference = timeDifference;
	}

	public boolean getThreadAliveFlag() {
		return threadAliveFlag;
	}

	public void setThreadAliveFlag(boolean threadAliveFlag) {
		this.threadAliveFlag = threadAliveFlag;
	}
	
	
	
	
	
	
	
	
	
}
