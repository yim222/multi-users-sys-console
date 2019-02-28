package main.java.draft;

import java.util.Scanner;

//Stackoverflow question
public class SOQ {
	
	public static void main(String[] args) {
		
		//How to do combination of some conditions. 
		//Trying on user. 
		UserUI userUI = new UserUI();
		
		System.out.println("If User regualr just 1-7, if admin 8-12 too");
		Scanner sc = new Scanner (System.in);
		int input = sc.nextInt();
		userUI.manageOptions(input);
		
		
		//trying to do that on admin 
		System.out.println("If User regualr just 1-7, if admin 8-12 too");
		AdminUI adminUI = new AdminUI();
		input = sc.nextInt();
		adminUI.manageOptions(input);
		
		
		sc.close();
		
	}
	
	
}


class UserUI{
	
	boolean admin = false;
	
	public void manageOptions(int  input){
		
		if(input > 0 && input < 8){
			
			userMethods(input);
		}
		
		else if((input > 7 && input < 13) && admin){
			
			AdminUI adminUI = new AdminUI();
			adminUI.manageAdminOptions(input);
			
		}
		
		else{
			
			System.out.println("Not proper option");
		}
		
	}
	
	public void userMethods(int input){
		
		System.out.println("User Methods - method " + input);
	}
	
	
}

class AdminUI extends UserUI{
	
	public AdminUI(){
		
		admin = true;
	}
	
	public void manageAdminOptions(int input){
		adminMethods(input);
		
	}
	
	public void adminMethods(int input){
		System.out.println("Admin Methods - method " + input);
		
	}
	
	
}