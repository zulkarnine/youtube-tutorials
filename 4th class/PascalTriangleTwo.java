import java.util.Scanner;


public class PascalTriangleTwo {

	static Scanner myScanner=new Scanner(System.in);
	public static void main(String[] args) {
		prln("give -1 to exit");
		pr("Give the length of pascal's triangle's length: ");
		int userInput=myScanner.nextInt();
		while (userInput!=-1) {
			if (userInput<-1||userInput>20) {
				prln("please enter something between -1~20");
				userInput=myScanner.nextInt();
				continue;
			}
			
			printPascalTriangle(userInput);
			prln("\n\n");
			prln("please enter something between -1~20");
			userInput=myScanner.nextInt();

		}
		
	}
	
	static void printPascalTriangle(int length){
		int[][] pascal=new int[length][length];
		
		for (int i = 0; i < length; i++) {
			pascal[0][i]=1;
			pascal[i][0]=1;
		}
		
		for (int row = 1; row < length; row++) {
			for (int column = 1; column < length-row; column++) {
				pascal[row][column]=pascal[row][column-1]+pascal[row-1][column];
			}
			
		}
		
		for (int row = 0; row < length; row++) {
			for (int column = 0; column < length-row; column++) {
				pr(pascal[row][column]+"\t");
			}
			prln("\n");
			
		}
		
		
		
	}
	

	static void prln(Object anyObject){
		System.out.println(anyObject);
	}
	
	static void pr(Object anyObject){
		System.out.print(anyObject);
	}

}
