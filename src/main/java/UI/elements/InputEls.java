package main.java.UI.elements;

import java.util.Scanner;

public class InputEls {
	
	
	Scanner input1 = new Scanner(System.in);
	
	
	
	/**
	 * Some KINDS OF basic INPUTS.
	 */
	
	//simple get and retunr the message 
	public String getUserChoiceSimple(){
		
		String userChoice = "";
		//Trying to make scanner that closed
		/*
		Scanner sc = new Scanner(System.in);
		if(sc.hasNextLine()){
			userChoice = sc.nextLine();
			
		}
		//
		
		
		//System.out.println(userChoice);
		sc.close();
		*/
		userChoice = input1.nextLine();
		return userChoice;
	}
	
	
	//get user choice, specific range
	public String getUserChoice(int from , int to  ){
		
		String userChoice = input1.nextLine();
		
		try {
			
			if (Integer.parseInt(userChoice) > to  || Integer.parseInt(userChoice) < from ){
		
				System.out.println("please choose the correct value ");
		
				return getUserChoice(from, to);
		
		}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
				System.out.println("please choose the correct value Not String!!!");
		
				return getUserChoice(from, to);
		}
		
		
		return userChoice;
	
	}
	
	//get user choice, specific range + specific value as list
	//get user choice(2, 10 , "g", "gh"...) 
	public String getUserChoice(int from , int to, String...values  ){
		String userChoice = "";
		if(input1.hasNextLine()){
			userChoice= input1.nextLine();
		}
		
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
		
				return getUserChoice(from, to, values);
		
		}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
				System.out.println("please choose the correct value Not String!!!");
		
				return getUserChoice(from, to, values );
		}
		
		
		return userChoice;
	
	}
	
	public void inputDelay(){
		System.out.println("\n***Press Enter for continue***");
		input1.nextLine();
	}
	
	public int inputInt(){
		
		int number = input1.nextInt();
		return number;
	}
}
