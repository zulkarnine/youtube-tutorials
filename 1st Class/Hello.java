import java.util.Scanner;


public class Hello {
	static Scanner sc=new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("May I know your name please?\n");
		String name=sc.nextLine();
		
		System.out.println("Hello!!"+name);
	}

}
