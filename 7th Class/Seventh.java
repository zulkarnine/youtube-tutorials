package SeventhLecture;

import java.util.Scanner;

public class Seventh {
	
	static Scanner myScanner= new Scanner(System.in);
	private static OurThread thread1 = new OurThread("Child 1",0,null,1,500,true);
	private static OurThread thread2 = new OurThread("Child 2",100,thread1,10,1000,true);
	private static OurThread thread3 = new OurThread("Child 3",1000,thread2,100,2000,true);
	
	
	public static void main(String[] args) {
		
		
		thread1.start();thread2.start();thread3.start();
		
		System.out.println("Please write a Command: ");
		
		String commandString=myScanner.nextLine();
		
		while (!commandString.toLowerCase().equals("exit")) {
			if (commandString.equals("1")) {
				System.out.println("Counter in Thread 1: "+thread1.getCounter());
				threadStatus();
			}else if (commandString.equals("2")) {
				System.out.println("Counter in Thread 2: "+thread2.getCounter());
				threadStatus();
			}else if (commandString.equals("3")) {
				System.out.println("Counter in Thread 3: "+thread3.getCounter());
				threadStatus();
			}else if (commandString.equals("1S")) {
				thread2.stopThread();
				
				System.out.println("Thread 1 Stopped");
				threadStatus();
			}else if (commandString.equals("2S")) {
				thread2.stopThread();
				
				System.out.println("Thread 2 Stopped");
				threadStatus();
			}else if (commandString.equals("3S")) {
				thread3.stopThread();
				
				System.out.println("Thread 3 Stopped");
				threadStatus();
			}else {
				System.out.println("Please give a proper command!!");
			}
			
			
			commandString=myScanner.nextLine();
			
			
		}
		
				
	}
	
	private static void threadStatus() {
		System.out.println("Thread 1 is alive: "+ thread1.isAlive());
		System.out.println("Thread 2 is alive: "+ thread2.isAlive());
		System.out.println("Thread 3 is alive: "+ thread3.isAlive());
	}

}
