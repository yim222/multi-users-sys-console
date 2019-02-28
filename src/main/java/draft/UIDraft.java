package main.java.draft;

import java.util.Scanner;

import main.java.objects.MultiUsersSystem;
import main.java.objects.User;
import main.java.system.constants.DBConstants;

public class UIDraft {
	
	//User that can change the email
	
	
	String userName = "superAdmin", password = "555A";
	User user = MultiUsersSystem.login(userName, password);
	
	
	
	public static void main(String[] args) {
		//local variables:
		
		UIDraft obj1 = new UIDraft();
		Outputs outputs1 = new Outputs();
		Inputs inputs1 = new Inputs();
		MainIO mainIO = new MainIO();
		
		/*************************/
		
		
		outputs1.choosingOption();
		String userChoice = inputs1.getUserChoice();
		System.out.println(userChoice);
		if (userChoice.equals("1")){
			obj1.user.displayMessages();
			System.out.println(outputs1.changeMessages[0]);
			userChoice = inputs1.getUserChoice2();
			System.out.println(userChoice);
			int x = Integer.parseInt(userChoice);
			//USER_MESSAGES_KEYS
			System.out.println(outputs1.changeMessages[1]);
			userChoice = inputs1.getUserChoice();
			System.out.println("This is the new message : " + userChoice);
			obj1.user.changeMessage(DBConstants.USER_MESSAGES_KEYS[x-1], userChoice);
			
			System.out.println("Congratulation! this is your new messages : ");
			obj1.user.displayMessages();
		
			
		}
		
		else{
			
			System.out.println("Goodbye");
		}
		
		//*************************************************************//
		
		
		
		int min = 5;
		int max = 13;
		String[] values = {"p", "b", "c", "i", "y", "!"};
		outputs1.displayArbitraryValues(min, max, values);
		String str1 = inputs1.getUserChoice5(min ,max, values);
		System.out.println("Good - U choose " + str1);
		outputs1.displayArbitraryValues(1, 4, "x");
		str1 = inputs1.getUserChoice5(1, 4, "x");
		System.out.println("good - 2 = " + str1);
		while(true){
			
			mainIO.example1(min, max, values);
			
		}
		
		
		
		
		
		
		
	}
	
	
	
}

class Outputs{
	
	
	String[] changeMessages = {"Please choose what message u want to chagne : 1 or 2", "please right the new message u want to insert: "};
	public void choosingOption(){
		
		System.out.println("Please choose one of the options :\n"
				+ "1-change my messages || x- exit"); 
		
		
		
	}
	
	//Show arbitrary options 
	public void displayArbitraryValues(int from, int to, String ...values){
		System.out.println("Please choose one of the options: ");
		for(int i = from; i <= to ; i++){
			
			System.out.print(i + " , ");
			
		}
		
		for(String str : values){
			System.out.print(str + " , ");
		}
		System.out.println();
	}
}

class Inputs{
	
	Scanner input1  = new Scanner(System.in);//.useDelimiter("%s")
	
	public String getUserChoice(){
		
		String userChoice = input1.nextLine();
		return userChoice;
	}
	public String getUserChoice2(){
		
		String userChoice = input1.nextLine();
		try {
			if (Integer.parseInt(userChoice) >2 ){
			
			System.out.println("please choose the correct value ");
			
			return getUserChoice2();
		}
		} catch (Exception e) {
			System.out.println("please choose the correct value ");
			
			return getUserChoice2();
		}
		
		return userChoice;
	}
	
	
	public String getUserChoice3(int max ){
		
		String userChoice = input1.nextLine();
		
		try {
			
			if (Integer.parseInt(userChoice) > max  ){
		
				System.out.println("please choose the correct value ");
		
				return getUserChoice3(max);
		
		}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
				System.out.println("please choose the correct value Not String!!!");
		
				return getUserChoice3(max);
		}
		
		
		return userChoice;
	
	}
	
	public String getUserChoice4(int from , int to  ){
		
		String userChoice = input1.nextLine();
		
		try {
			
			if (Integer.parseInt(userChoice) > to  || Integer.parseInt(userChoice) < from ){
		
				System.out.println("please choose the correct value ");
		
				return getUserChoice4(from, to);
		
		}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
				System.out.println("please choose the correct value Not String!!!");
		
				return getUserChoice4(from, to);
		}
		
		
		return userChoice;
	
	}
	
	public String getUserChoice5(int from , int to, String...values  ){
		
		String userChoice = input1.nextLine();
		boolean valueChoosed = false; 
		for(String value : values){
			
			if (userChoice.equals(value)){
				
				valueChoosed = true;
				break; 
				
			}
		}
		
		try {
			
			if (!valueChoosed && (Integer.parseInt(userChoice) > to  || Integer.parseInt(userChoice) < from ) ){
			//if (!valueChoosed || Integer.parseInt(userChoice) > to  || Integer.parseInt(userChoice) < from  ){
				System.out.println("please choose the correct value ");
		
				return getUserChoice5(from, to, values);
		
		}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
				System.out.println("please choose the correct value Not String!!!");
		
				return getUserChoice5(from, to, values );
		}
		
		
		return userChoice;
	
	}
	//int...[], String[]
	
}

class MainIO{
	Outputs outputs1 = new Outputs();
	Inputs inputs1 = new Inputs();
	String choice = "";
	public void userChangeMessages(){
		
		
	}
	
	public void example1(int from, int to, String...values){
		
		outputs1.displayArbitraryValues(from, to, values);
		choice =  inputs1.getUserChoice5(from, to, values);
		System.out.println("your choice is : " + choice);
		
		
	}
	
}

