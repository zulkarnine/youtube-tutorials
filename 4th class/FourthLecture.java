import java.util.Iterator;
import java.util.Scanner;


public class FourthLecture {
	static Scanner myScanner=new Scanner(System.in);

	public static void main(String[] args) {
		int a,b;
		a=10;
		b=20;
		
//		int[] numberArray=new int[100];
//		int i=100;
//		while (i>0) {
//			numberArray[i-1]=(i-1)*7;
//			i--;
//		}
		
		int[][] twoDArr=new int[2][4];
		
		twoDArr[0][0]=1;
		twoDArr[0][1]=2;
		twoDArr[0][2]=4;
		twoDArr[0][3]=5;

		twoDArr[1][0]=2;
		twoDArr[1][1]=4;
		twoDArr[1][2]=5;
		twoDArr[1][3]=9;
		
//		prln("at 2dArray[1][2]: "+twoDArray[1][2]);
//		1 2 4 5
//		2 4 5 9
		
		
//		prln("please give your input:");
		
		//taking the input
//		for (int row = 0; row < 2; row++) {
//			for (int column = 0; column < 4; column++) {
//				pr("twoDArray["+row+"]["+column+"] :");
//				twoDArr[row][column]=myScanner.nextInt();
//				
//			}
//			//prln("\n");
//		}
		
		
		//now printing the 2darray
		
		int userInput=0;
		try {
//			for (int row = 0; row < 2; row++) {
//				for (int column = 0; column < 4; column++) {
//					pr(twoDArr[row][column]+"\t");
//				}
//				
//				prln("\n");
//				
//			}
			
			pr("Please give a number:");
			userInput=myScanner.nextInt();
			while (userInput<50) {
				
				prln((100/userInput));
				//pr("Please give a number:");
				//userInput=myScanner.nextInt();
				userInput++;
			}
			
			
			
		} catch (Exception unknownException) {
			
			unknownException.printStackTrace();
			
		} finally{
			//userInput;
			prln("came out of try-catch block, with userInput: "+userInput);
		}
		
		
		
		
		
		
		prln("thank you for using our program");
		
		
		
//		for (int j =3; j < 100; j*=2) {
//			prln(j);
//			
//		}
		
		
	}
	
	
	static int calculateNumber(int number1,int number2){
		number1+=number2;
		return number1;
	}
	
	static void prln(Object anyObject){
		System.out.println(anyObject);
	}
	
	static void pr(Object anyObject){
		System.out.print(anyObject);
	}

}
