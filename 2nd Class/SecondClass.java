import java.util.Scanner;


public class SecondClass {

	static Scanner myScanner=new Scanner(System.in);
	
	public static void main(String[] args) {
		int firstIntNumber;						//declaring variables
		int secondIntNumber;
		char[] cards={'4', '2','3','7','B','K'};
		     // Index: 0    1   2   3   4   5
		int[] books=new int[4];
		
		books[0]=12;
		books[1]=10;
		books[2]=1;
		books[3]=90;
		
		
		firstIntNumber=11;  					//this is a starting value
		secondIntNumber=2;
		
		System.out.println("------At The Beginning-----");
		printTwoNumbers(firstIntNumber, secondIntNumber);
		
		float myFloatNumber=10;
		myFloatNumber/=7;
												//following line is commented out
		//secondIntNumber=secondIntNumber+10;
		
		secondIntNumber/=2;
		
		/*
		 * this is a multi line comment
		 * 
		 */
		
		System.out.println("------ After secondIntNumber/=2 -----");
		printTwoNumbers(firstIntNumber, secondIntNumber);
		
		secondIntNumber++;  //secondIntNumber+=1; secondIntNumber=secondIntNumber+1;
		
		System.out.println("------ After secondIntNumber++ -----");
		printTwoNumbers(firstIntNumber, secondIntNumber);
		
	
		books[3]+=books[1];
		
		System.out.println("------ After books[3]+=books[1] -----");
		System.out.println("books[3]= "+ books[3]+ "\n");
		
		firstIntNumber=(56+34)*(7-9)/4;
		System.out.println("------ After firstIntNumber=(56+34)*(7-9)/4 -----");
		printTwoNumbers(firstIntNumber, secondIntNumber);
		
		
		//secondIntNumber--;
		
		
		
		
		System.out.print("Please give us a number to be multiplied by 100: ");
		firstIntNumber=myScanner.nextInt();
		
		System.out.println("------ After Getting Input -----");
		printTwoNumbers(firstIntNumber, secondIntNumber);
		
		
		int resultNew=prokriya(firstIntNumber);
		int result=firstIntNumber%secondIntNumber;
		
		//--------------------------Printing all the results-----------------------------
		
		System.out.println("firstIntNumber: "+firstIntNumber);
		System.out.println("secondIntNumber: "+secondIntNumber);
		System.out.println("remainder after firstIntNumber%secondIntNumber: "+result);
		System.out.println("myFloatNumber: "+myFloatNumber);
		System.out.println("cards[4]: "+cards[4]);
		System.out.println("books[3]: "+books[3]);
		System.out.println("resultNew: "+resultNew);

		
	}
	
	static public int prokriya(int firstValue) {
		firstValue*=100;
		return firstValue;
	}
	
	static public void printTwoNumbers(int firstNumber, int secondNumber){
		System.out.println("First Number: "+firstNumber+"\t SecondNumber: "+secondNumber);
		System.out.print("\n");
	}

}
